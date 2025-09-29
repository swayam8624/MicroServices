package com.retail.product.repository;

import com.retail.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(4); // Start from 4 since we already have 3 products

    public ProductRepository() {
        // Initialize with some sample data
        products.add(new Product(1L, "Laptop", "High-performance laptop", 1200.0, 10));
        products.add(new Product(2L, "Smartphone", "Latest smartphone model", 800.0, 25));
        products.add(new Product(3L, "Headphones", "Noise-cancelling headphones", 200.0, 50));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(product -> product.getId() != null && product.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idGenerator.getAndIncrement());
        }
        // Check if product already exists
        Optional<Product> existingProduct = findById(product.getId());
        if (existingProduct.isPresent()) {
            // Update existing product
            products.removeIf(p -> p.getId().equals(product.getId()));
        }
        products.add(product);
        return product;
    }

    public void deleteById(Long id) {
        products.removeIf(product -> product.getId() != null && product.getId().equals(id));
    }
}