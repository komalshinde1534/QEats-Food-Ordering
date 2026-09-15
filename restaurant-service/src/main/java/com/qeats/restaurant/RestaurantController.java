package com.qeats.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantRepository repository;
    public RestaurantController(RestaurantRepository repository){this.repository=repository;}
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));}
    @GetMapping
    public List<Restaurant> byCity(@RequestParam String city){return repository.findByCityIgnoreCaseAndAvailableTrue(city);}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant create(@RequestBody Restaurant restaurant){return repository.save(restaurant);}
}
