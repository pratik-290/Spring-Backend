package org.example.controller;

import org.example.model.Restaurant;
import org.example.model.Food;
import org.example.service.FoodService;
import org.example.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final FoodService foodService;

    public RestaurantController(
            RestaurantService restaurantService,
            FoodService foodService) {

        this.restaurantService = restaurantService;
        this.foodService = foodService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/{restaurantId}/foods")
    public List<Food> getRestaurantFoods(
            @PathVariable Long restaurantId) {

        return foodService.getFoodsByRestaurantId(restaurantId);
    }
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }
}