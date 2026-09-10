package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

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

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.placeOrderByUsername(username);
    }

    @GetMapping
    public List<Order> getOrderHistory() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.getOrderHistory(username);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return orderService.getOrderById(id, username);
    }
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderService.updateOrderStatus(id, status);
    }
}