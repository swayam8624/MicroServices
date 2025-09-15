#!/bin/bash

# Script to test all microservices patterns

echo "Testing Event-Driven Pattern..."
cd DA2/event-driven-pattern && mvn spring-boot:run &
EVENT_PID=$!
sleep 10
curl -X POST http://localhost:9005/publish -H "Content-Type: application/json" -d '{"message": "Hello World", "timestamp": "2025-09-15T21:40:00"}'
pkill -f "event-driven-pattern"

echo ""
echo "Testing CQRS Pattern..."
cd ../cqrs-pattern && mvn spring-boot:run &
CQRS_PID=$!
sleep 10
curl -X POST http://localhost:9006/items -H "Content-Type: application/json" -d '{"name": "Test Item", "description": "This is a test item"}'
curl http://localhost:9006/items
pkill -f "cqrs-pattern"

echo ""
echo "Testing DB-Per-Service Pattern..."
cd ../db-per-service-pattern && mvn spring-boot:run &
DB_PID=$!
sleep 10
curl -X POST http://localhost:9008/products -H "Content-Type: application/json" -d '{"name": "Test Product", "price": 29.99}'
curl http://localhost:9008/products
pkill -f "db-per-service-pattern"

echo ""
echo "Testing Proxy-Adapter Pattern..."
cd ../proxy-adapter-pattern && mvn spring-boot:run &
PROXY_PID=$!
sleep 10
curl -X POST "http://localhost:9002/process?amount=50.0"
curl -X POST "http://localhost:9002/process?amount=150.0"
pkill -f "proxy-adapter-pattern"

echo ""
echo "Testing Saga Pattern..."
cd ../saga-pattern && mvn spring-boot:run &
SAGA_PID=$!
sleep 10
curl -X POST "http://localhost:9007/place?amount=50.0"
curl -X POST "http://localhost:9007/place?amount=150.0"
pkill -f "saga-pattern"

echo ""
echo "All tests completed!"