package com.qeats.order;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/orders")
public class OrderController {
 private final OrderService service; public OrderController(OrderService service){this.service=service;}
 @GetMapping("/restaurant/{id}") public RestaurantDto restaurant(@PathVariable Long id){return service.getRestaurant(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public FoodOrder create(@Valid @RequestBody CreateOrderRequest request){return service.create(request);}
}
