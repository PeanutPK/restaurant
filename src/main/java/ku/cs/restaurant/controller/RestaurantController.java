package ku.cs.restaurant.controller;

import ku.cs.restaurant.entity.Restaurant;
import ku.cs.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RestaurantController {

    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return service.getAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return service.getRestaurantById(id);
    }

    @PostMapping("/restaurants")
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return service.create(restaurant);
    }

    @PutMapping("/restaurants")
    public Restaurant update(@RequestBody Restaurant restaurant) {
        return service.update(restaurant);
    }
}
