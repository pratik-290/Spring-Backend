package org.example.controller;

import org.example.dto.CartResponse;
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
            @RequestParam Long foodId,
            @RequestParam Integer quantity) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return cartService.addToCartByEmail(
                email,
                foodId,
                quantity
        );
    }

    @GetMapping
    public List<CartResponse> getUserCart() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return cartService.getUserCartByEmail(email);
    }

    @DeleteMapping("/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartService.removeFromCart(id);

        return "Cart item removed successfully!";
    }
}