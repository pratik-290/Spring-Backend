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

    // Get All Foods
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Get Food By ID
    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Food not found with id: " + id)
                );
    }

    // Delete Food
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}