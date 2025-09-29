package com.retail.order.repository;

import com.retail.order.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public OrderRepository() {
        // Initialize with empty list
    }

    public List<Order> findAll() {
        return orders;
    }

    public Optional<Order> findById(Long id) {
        return orders.stream().filter(order -> order.getId() != null && order.getId().equals(id)).findFirst();
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(idGenerator.getAndIncrement());
        }
        // Check if order already exists
        Optional<Order> existingOrder = findById(order.getId());
        if (existingOrder.isPresent()) {
            // Update existing order
            orders.removeIf(o -> o.getId().equals(order.getId()));
        }
        orders.add(order);
        return order;
    }

    public void deleteById(Long id) {
        orders.removeIf(order -> order.getId() != null && order.getId().equals(id));
    }
}