package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.placeOrderByEmail(email);
    }

    @GetMapping
    public List<Order> getOrderHistory() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.getOrderHistory(email);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.getOrderById(id, email);
    }
}