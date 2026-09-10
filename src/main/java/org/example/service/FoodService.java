package org.example.service;

import org.example.model.Food;
import org.example.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    public Food createFood(Food food) {
        return foodRepository.save(food);
    }


    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }


    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Food not found with id: " + id)
                );
    }


    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
    public List<Food> getFoodsByRestaurantId(Long restaurantId) {
        return foodRepository.findByRestaurant_Id(restaurantId);
    }
}