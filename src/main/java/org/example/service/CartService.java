package org.example.service;

import org.example.model.Cart;
import org.example.model.Food;
import org.example.repository.CartRepository;
import org.example.repository.FoodRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       FoodRepository foodRepository,
                        UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(Long userId, Long foodId, Integer quantity) {

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() ->
                        new RuntimeException("Food not found with id: " + foodId)
                );

        Cart cart = new Cart();

        cart.setUserId(userId);
        cart.setFoodId(foodId);
        cart.setQuantity(quantity);

        double totalPrice = food.getPrice() * quantity;
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }
    public List<Cart> getUserCart(Long userId) {

        return cartRepository.findAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .toList();
    }

    public void removeFromCart(Long id) {

        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart item not found with id: " + id);
        }

        cartRepository.deleteById(id);
    }
    public List<Cart> getUserCartByUsername(String username) {

        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                )
                .getId();

        return getUserCart(userId);
    }
}