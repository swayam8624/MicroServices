# Microservices Patterns - Test Results

## 1. Event-Driven Pattern
- **Port**: 9005
- **Endpoint**: POST /publish
- **Test Command**: `curl -X POST http://localhost:9005/publish -H "Content-Type: application/json" -d '{"message": "Hello World", "timestamp": "2025-09-15T21:40:00"}'`
- **Response**: `PUBLISHED`

## 2. CQRS Pattern
- **Port**: 9006
- **Endpoints**: 
  - POST /items
  - GET /items
- **Test Commands**:
  - Create item: `curl -X POST http://localhost:9006/items -H "Content-Type: application/json" -d '{"name": "Test Item", "description": "This is a test item"}'`
  - List items: `curl http://localhost:9006/items`
- **Responses**:
  - Create: `{"id":1,"name":"Test Item"}`
  - List: `[{"id":1,"name":"Test Item"}]`

## 3. DB-Per-Service Pattern
- **Port**: 9008
- **Endpoints**: 
  - POST /products
  - GET /products
- **Test Commands**:
  - Create product: `curl -X POST http://localhost:9008/products -H "Content-Type: application/json" -d '{"name": "Test Product", "price": 29.99}'`
  - List products: `curl http://localhost:9008/products`
- **Responses**:
  - Create: `{"id":1,"name":"Test Product"}`
  - List: `[{"id":1,"name":"Test Product"}]`

## 4. Proxy-Adapter Pattern
- **Port**: 9002
- **Endpoint**: POST /process
- **Test Commands**:
  - Approved: `curl -X POST "http://localhost:9002/process?amount=50.0"`
  - Declined: `curl -X POST "http://localhost:9002/process?amount=150.0"`
- **Responses**:
  - Approved: `EXTERNAL_APPROVED`
  - Declined: `EXTERNAL_DECLINED`

## 5. Saga Pattern
- **Port**: 9007
- **Endpoint**: POST /place
- **Test Commands**:
  - Success: `curl -X POST "http://localhost:9007/place?amount=50.0"`
  - Compensation: `curl -X POST "http://localhost:9007/place?amount=150.0"`
- **Responses**:
  - Success: `COMPLETED`
  - Compensation: `FAILED_COMPENSATED`
- **Console Output**:
  - Success flow: "Reserve OK, Payment OK, commit"
  - Compensation flow: "Reserve OK, Payment failed, compensating: release reservation"