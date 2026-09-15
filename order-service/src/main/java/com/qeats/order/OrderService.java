package com.qeats.order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Duration;

@Service
public class OrderService {
 private final FoodOrderRepository repository; private final RestaurantClient restaurantClient; private final StringRedisTemplate redis; private final ObjectMapper mapper;
 public OrderService(FoodOrderRepository repository,RestaurantClient restaurantClient,StringRedisTemplate redis,ObjectMapper mapper){this.repository=repository;this.restaurantClient=restaurantClient;this.redis=redis;this.mapper=mapper;}
 public RestaurantDto getRestaurant(Long id){
   String key="restaurant:"+id; String cached=redis.opsForValue().get(key);
   if(cached!=null) try{return mapper.readValue(cached,RestaurantDto.class);} catch(JsonProcessingException ignored){}
   RestaurantDto dto=restaurantClient.getRestaurant(id);
   try{redis.opsForValue().set(key,mapper.writeValueAsString(dto),Duration.ofMinutes(10));}catch(JsonProcessingException ignored){}
   return dto;
 }
 public FoodOrder create(CreateOrderRequest r){
   RestaurantDto restaurant=getRestaurant(r.restaurantId());
   if(restaurant==null || !restaurant.available()) throw new IllegalStateException("Restaurant unavailable");
   if(restaurant.menu()==null || restaurant.menu().stream().noneMatch(m->m.equalsIgnoreCase(r.item()))) throw new IllegalArgumentException("Item not available: "+r.item());
   BigDecimal total=r.unitPrice().multiply(BigDecimal.valueOf(r.quantity()));
   return repository.save(new FoodOrder(r.restaurantId(),r.customerName(),r.item(),r.quantity(),total,"PLACED"));
 }
}
