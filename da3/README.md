# Retail E-Commerce Microservices

This project implements a containerized microservices architecture for a retail company's e-commerce platform with three services:

1. Product Service - manages product catalog details
2. Order Service - handles customer orders and depends on the Product Service
3. Payment Service - calculates the total bill based on the Order Service

## Prerequisites

- Java 8
- Maven
- Docker (optional, for containerized deployment)

## Project Structure

```
da3/
├── product-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── order-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── payment-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── docker-compose.yml
├── Microservices_Architecture_Report.md
└── README.md
```

## Building the Services

### Build with Maven

Navigate to each service directory and build with Maven:

```bash
# Build Product Service
cd product-service
mvn clean package

# Build Order Service
cd ../order-service
mvn clean package

# Build Payment Service
cd ../payment-service
mvn clean package
```

## Running the Services

### Option 1: Run Directly with Java

After building each service, run them using:

```bash
# Run Product Service (port 8081)
cd product-service
java -jar target/product-service-1.0.0.jar

# Run Order Service (port 8082)
cd ../order-service
java -jar target/order-service-1.0.0.jar

# Run Payment Service (port 8083)
cd ../payment-service
java -jar target/payment-service-1.0.0.jar
```

### Option 2: Run with Docker

Build and run the services using Docker Compose:

```bash
# Build and start all services
docker-compose up --build

# Stop all services
docker-compose down
```

## API Endpoints

### Product Service (http://localhost:9081)

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update a product
- `DELETE /api/products/{id}` - Delete a product

### Order Service (http://localhost:9082)

- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update an order
- `DELETE /api/orders/{id}` - Delete an order

### Payment Service (http://localhost:9083)

- `GET /api/payments` - Get all payments
- `GET /api/payments/{id}` - Get payment by ID
- `POST /api/payments` - Create a new payment
- `PUT /api/payments/{id}` - Update a payment
- `DELETE /api/payments/{id}` - Delete a payment

## Testing the Services

### 1. Add Products

```bash
curl -X GET http://localhost:9081/api/products
```

### 2. Create an Order

```bash
curl -X POST http://localhost:9082/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId": 1, "items": [{"productId": 1, "quantity": 2}]}'
```

### 3. Process a Payment

```bash
curl -X POST http://localhost:9083/api/payments \
  -H "Content-Type: application/json" \
  -d '{"orderId": 1, "paymentMethod": "CREDIT_CARD"}'
```

## Service Dependencies

- Payment Service depends on Order Service
- Order Service depends on Product Service

## Troubleshooting

### Java Version Issues

If you encounter version compatibility issues, ensure you're using Java 8 and that the Spring Boot version in pom.xml is compatible.

### Port Conflicts

If the default ports are in use, modify the `application.properties` file in each service to use different ports.

### Docker Issues

If Docker fails to build or run the services, ensure Docker is running and you have sufficient permissions.
