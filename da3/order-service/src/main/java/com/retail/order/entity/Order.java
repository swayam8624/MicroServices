package com.retail.order.entity;

import java.util.List;

public class Order {
    private Long id;
    private Long customerId;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;

    public Order() {}

    public Order(Long id, Long customerId, List<OrderItem> items, double totalAmount, String status) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}