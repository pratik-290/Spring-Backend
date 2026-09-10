package org.example.controller;

import org.example.model.Cart;
import org.example.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart addToCart(
            @RequestParam Long userId,
            @RequestParam Long foodId,
            @RequestParam Integer quantity) {

        return cartService.addToCart(userId, foodId, quantity);
    }

    @GetMapping
    public List<Cart> getUserCart() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return cartService.getUserCartByUsername(username);
    }

    @DeleteMapping("/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartService.removeFromCart(id);

        return "Cart item removed successfully!";
    }
}