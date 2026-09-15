package com.qeats.order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@Component
public class RestaurantClient {
 private final RestClient client;
 public RestaurantClient(RestClient.Builder builder){this.client=builder.baseUrl("http://localhost:8081").build();}
 public RestaurantDto getRestaurant(Long id){return client.get().uri("/api/v1/restaurants/{id}",id).retrieve().body(RestaurantDto.class);}
}
