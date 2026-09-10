package org.example.controller;

import org.example.model.Payment;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import org.example.dto.PaymentVerifyRequest;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}")
    public Payment createPayment(@PathVariable Long orderId) throws Exception{
        return paymentService.createPayment(orderId);
    }
    @PostMapping("/verify")
    public String verifyPayment(
            @RequestBody PaymentVerifyRequest request) throws Exception {

        boolean valid = paymentService.verifyPayment(
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature()
        );

        if (valid) {
            return "Payment verified successfully!";
        }

        return "Payment verification failed!";
    }
}