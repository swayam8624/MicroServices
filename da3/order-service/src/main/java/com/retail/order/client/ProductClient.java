package com.retail.order.client;

import com.retail.order.entity.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class ProductClient {
    
    private final RestTemplate restTemplate;
    
    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Optional<OrderItem> getProductById(Long productId) {
        try {
            String url = "http://product-service:8081/api/products/" + productId;
            ResponseEntity<ProductResponse> response = restTemplate.getForEntity(url, ProductResponse.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ProductResponse product = response.getBody();
                OrderItem item = new OrderItem();
                item.setProductId(product.getId());
                item.setProductName(product.getName());
                item.setPrice(product.getPrice());
                return Optional.of(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    // Inner class to map the product response
    public static class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private double price;
        private int quantity;
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}