package com.retail.order.controller;

import com.retail.order.entity.Order;
import com.retail.order.entity.OrderItem;
import com.retail.order.repository.OrderRepository;
import com.retail.order.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductClient productClient;
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Validate product availability
        for (OrderItem item : order.getItems()) {
            Optional<OrderItem> productInfo = productClient.getProductById(item.getProductId());
            if (!productInfo.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Update item with latest product info
            OrderItem product = productInfo.get();
            item.setProductName(product.getProductName());
            item.setPrice(product.getPrice());
        }
        
        // Calculate total amount
        double totalAmount = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);
        order.setStatus("CREATED");
        
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setCustomerId(orderDetails.getCustomerId());
            order.setItems(orderDetails.getItems());
            order.setStatus(orderDetails.getStatus());
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}