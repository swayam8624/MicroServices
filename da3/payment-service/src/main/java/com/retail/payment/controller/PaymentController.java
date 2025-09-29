package com.retail.payment.controller;

import com.retail.payment.entity.Payment;
import com.retail.payment.repository.PaymentRepository;
import com.retail.payment.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderClient orderClient;
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        // Validate order exists and get amount
        Optional<Double> orderAmount = orderClient.getOrderAmount(payment.getOrderId());
        if (!orderAmount.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        // Set payment amount from order
        payment.setAmount(orderAmount.get());
        payment.setStatus("COMPLETED");
        
        Payment savedPayment = paymentRepository.save(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setOrderId(paymentDetails.getOrderId());
            payment.setAmount(paymentDetails.getAmount());
            payment.setStatus(paymentDetails.getStatus());
            payment.setPaymentMethod(paymentDetails.getPaymentMethod());
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}