package org.example.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.example.model.Payment;
import org.example.repository.OrderRepository;
import org.example.repository.PaymentRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final RazorpayClient razorpayClient;

    public PaymentService(PaymentRepository paymentRepository,
                          OrderRepository orderRepository,
                          RazorpayClient razorpayClient) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.razorpayClient = razorpayClient;
    }

    public Payment createPayment(Long orderId) throws Exception {


        org.example.model.Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + orderId)
                );


        int amountInPaise = (int) Math.round(order.getTotalAmount() * 100);


        JSONObject options = new JSONObject();
        options.put("amount", amountInPaise);
        options.put("currency", "INR");
        options.put("receipt", "order_" + orderId);

        Order razorpayOrder = razorpayClient.orders.create(options);


        Payment payment = new Payment();

        payment.setOrderId(orderId);
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("PENDING");


        payment.setPaymentId(razorpayOrder.get("id"));

        payment.setCreatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public boolean verifyPayment(
            String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature) throws Exception {

        String payload = razorpayOrderId + "|" + razorpayPaymentId;

        boolean valid = Utils.verifySignature(
                payload,
                razorpaySignature,
                "O3R6bAwMf9gkZcl4RJF4XrlK"
        );

        if (valid) {

            // Find payment using Razorpay Order ID
            Payment payment = paymentRepository.findAll()
                    .stream()
                    .filter(p -> razorpayOrderId.equals(p.getPaymentId()))
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Payment not found")
                    );

            // Payment successful
            payment.setStatus("SUCCESS");
            paymentRepository.save(payment);

            // Confirm our FoodUp order
            org.example.model.Order order = orderRepository
                    .findById(payment.getOrderId())
                    .orElseThrow(() ->
                            new RuntimeException("Order not found")
                    );

            order.setStatus("CONFIRMED");
            orderRepository.save(order);

            return true;
        }

        return false;
    }
}