package com.qeats.restaurant;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(RestaurantNotFoundException.class)
 public ResponseEntity<Map<String,String>> notFound(RestaurantNotFoundException ex){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("errorCode","RESTAURANT_NOT_FOUND","message",ex.getMessage()));}
}
