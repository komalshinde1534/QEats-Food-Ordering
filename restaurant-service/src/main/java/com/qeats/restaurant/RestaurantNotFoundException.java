package com.qeats.restaurant;
public class RestaurantNotFoundException extends RuntimeException { public RestaurantNotFoundException(Long id){super("Restaurant not found: "+id);} }
