package org.example.service;

import org.example.model.Cart;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.repository.CartRepository;
import org.example.repository.OrderItemRepository;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.example.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartRepository cartRepository,
                        UserRepository userRepository ) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Order placeOrder(Long userId) {

        List<Cart> cartItems = cartRepository.findAll()
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .toList();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = cartItems.stream()
                .mapToDouble(Cart::getTotalPrice)
                .sum();

        Order order = new Order();

        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (Cart cart : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrderId(savedOrder.getId());
            orderItem.setFoodId(cart.getFoodId());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(cart.getTotalPrice());

            orderItemRepository.save(orderItem);
            cartRepository.delete(cart);
        }

        return savedOrder;
    }
    public Order placeOrderByUsername(String username) {

        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                )
                .getId();

        return placeOrder(userId);
    }


    public List<Order> getOrderHistory(String username) {

        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                )
                .getId();

        return orderRepository.findByUserId(userId);
    }


    public Order getOrderById(Long id, String username) {

        Long userId = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                )
                .getId();

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id)
                );

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("You cannot access this order");
        }

        return order;
    }

    public Order updateOrderStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id)
                );

        order.setStatus(status);

        return orderRepository.save(order);
    }
}