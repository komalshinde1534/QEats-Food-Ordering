package com.qeats.restaurant;
import org.junit.jupiter.api.*; import org.mockito.*; import java.util.*;
import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;
class RestaurantControllerTest { @Mock RestaurantRepository repository; RestaurantController controller; @BeforeEach void setup(){MockitoAnnotations.openMocks(this);controller=new RestaurantController(repository);}
 @Test void shouldReturnRestaurant(){Restaurant r=new Restaurant("Spice Route","Indian","Pune",true,List.of("Paneer Tikka"));when(repository.findById(1L)).thenReturn(Optional.of(r));assertEquals("Spice Route",controller.get(1L).getName());}
 @Test void shouldThrowWhenMissing(){when(repository.findById(99L)).thenReturn(Optional.empty());assertThrows(RestaurantNotFoundException.class,()->controller.get(99L));}}
