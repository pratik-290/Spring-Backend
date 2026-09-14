package org.example.service;

import org.example.dto.CartResponse;
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

    public CartService(
            CartRepository cartRepository,
            FoodRepository foodRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(Long userId, Long foodId, Integer quantity) {

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Food not found with id: " + foodId
                        )
                );

        Cart cart = new Cart();

        cart.setUserId(userId);
        cart.setFoodId(foodId);
        cart.setQuantity(quantity);

        double totalPrice = food.getPrice() * quantity;

        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public List<CartResponse> getUserCart(Long userId) {

        List<Cart> carts = cartRepository.findByUserId(userId);

        return carts.stream()
                .map(cart -> {

                    Food food = foodRepository.findById(
                            cart.getFoodId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Food not found with id: "
                                            + cart.getFoodId()
                            )
                    );

                    return new CartResponse(
                            cart.getId(),
                            food.getId(),
                            food.getName(),
                            food.getImageUrl(),
                            food.getPrice(),
                            cart.getQuantity(),
                            cart.getTotalPrice()
                    );
                })
                .toList();
    }

    public void removeFromCart(Long id) {

        if (!cartRepository.existsById(id)) {
            throw new RuntimeException(
                    "Cart item not found with id: " + id
            );
        }

        cartRepository.deleteById(id);
    }

    public List<CartResponse> getUserCartByEmail(String email) {

        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                )
                .getId();

        return getUserCart(userId);
    }

    public Cart addToCartByEmail(
            String email,
            Long foodId,
            Integer quantity) {

        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                )
                .getId();

        return addToCart(userId, foodId, quantity);
    }
}