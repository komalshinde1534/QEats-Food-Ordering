package com.qeats.order;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestControllerAdvice public class OrderExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> bad(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("errorCode","INVALID_ORDER","message",e.getMessage()));}
 @ExceptionHandler(IllegalStateException.class) ResponseEntity<Map<String,String>> unavailable(IllegalStateException e){return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("errorCode","RESTAURANT_UNAVAILABLE","message",e.getMessage()));}
}
