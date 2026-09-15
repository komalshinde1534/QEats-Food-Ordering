package com.qeats.order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*; import org.mockito.*; import org.springframework.data.redis.core.*;
import java.math.BigDecimal; import java.util.List;
import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;
class OrderServiceTest {
 @Mock FoodOrderRepository repository; @Mock RestaurantClient client; @Mock StringRedisTemplate redis; @Mock ValueOperations<String,String> values;
 OrderService service; ObjectMapper mapper=new ObjectMapper();
 @BeforeEach void setup(){MockitoAnnotations.openMocks(this); when(redis.opsForValue()).thenReturn(values); service=new OrderService(repository,client,redis,mapper);}
 @Test void shouldUseCachedRestaurant() throws Exception {RestaurantDto dto=new RestaurantDto(1L,"Spice Route","Indian","Pune",true,List.of("Paneer Tikka")); when(values.get("restaurant:1")).thenReturn(mapper.writeValueAsString(dto)); assertEquals("Spice Route",service.getRestaurant(1L).name()); verifyNoInteractions(client);}
 @Test void shouldCreateOrderAndCalculateTotal(){RestaurantDto dto=new RestaurantDto(1L,"Spice Route","Indian","Pune",true,List.of("Paneer Tikka")); when(values.get("restaurant:1")).thenReturn(null); when(client.getRestaurant(1L)).thenReturn(dto); FoodOrder saved=new FoodOrder(1L,"Komal","Paneer Tikka",2,new BigDecimal("300.00"),"PLACED"); when(repository.save(any())).thenReturn(saved); FoodOrder result=service.create(new CreateOrderRequest(1L,"Komal","Paneer Tikka",2,new BigDecimal("150"))); assertEquals("PLACED",result.getStatus()); assertEquals(new BigDecimal("300.00"),result.getTotalAmount()); verify(repository).save(any(FoodOrder.class));}
 @Test void shouldRejectUnavailableItem(){RestaurantDto dto=new RestaurantDto(1L,"Spice Route","Indian","Pune",true,List.of("Dal Tadka")); when(values.get("restaurant:1")).thenReturn(null); when(client.getRestaurant(1L)).thenReturn(dto); assertThrows(IllegalArgumentException.class,()->service.create(new CreateOrderRequest(1L,"Komal","Pizza",1,new BigDecimal("100")))); verify(repository,never()).save(any());}
}
