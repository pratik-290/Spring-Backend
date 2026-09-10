package org.example.controller;

import org.example.model.Food;
import org.example.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodService.createFood(food);
    }


    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }


    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return "Food deleted successfully!";
    }
}