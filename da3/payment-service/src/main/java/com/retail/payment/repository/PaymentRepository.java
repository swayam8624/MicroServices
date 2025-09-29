package com.retail.payment.repository;

import com.retail.payment.entity.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public PaymentRepository() {
        // Initialize with empty list
    }

    public List<Payment> findAll() {
        return payments;
    }

    public Optional<Payment> findById(Long id) {
        return payments.stream().filter(payment -> payment.getId().equals(id)).findFirst();
    }

    public Payment save(Payment payment) {
        payments.add(payment);
        return payment;
    }

    public void deleteById(Long id) {
        payments.removeIf(payment -> payment.getId().equals(id));
    }
}