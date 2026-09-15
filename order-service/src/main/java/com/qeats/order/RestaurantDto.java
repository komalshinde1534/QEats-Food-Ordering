package com.qeats.order;
import java.util.List;
public record RestaurantDto(Long id,String name,String cuisine,String city,boolean available,List<String> menu) {}
