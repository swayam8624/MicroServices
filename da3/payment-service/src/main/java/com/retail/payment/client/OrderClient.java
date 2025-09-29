package com.retail.payment.client;

import com.retail.payment.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class OrderClient {
    
    private final RestTemplate restTemplate;
    
    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Optional<Double> getOrderAmount(Long orderId) {
        try {
            String url = "http://order-service:8082/api/orders/" + orderId;
            ResponseEntity<OrderResponse> response = restTemplate.getForEntity(url, OrderResponse.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                OrderResponse order = response.getBody();
                return Optional.of(order.getTotalAmount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    // Inner class to map the order response
    public static class OrderResponse {
        private Long id;
        private Long customerId;
        private double totalAmount;
        private String status;
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }
        
        public double getTotalAmount() { return totalAmount; }
        public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}