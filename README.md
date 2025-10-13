# Microservices CI/CD Demo

This is a demo project showcasing a CI/CD pipeline for microservices using Spring Boot, Docker, and GitHub Actions.

## Project Structure

```
microservices-demo-ci/
├── user-service/           # User microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── order-service/         # Order microservice
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── docker-compose.yml     # Docker Compose configuration
└── .github/workflows/     # GitHub Actions workflows
    └── ci.yml            # CI/CD pipeline definition
```

## Prerequisites

- Java 17 (OpenJDK or Temurin)
- Maven 3.9+
- Docker Desktop (running)
- Git
- GitHub account

## Getting Started

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd microservices-demo-ci
```

### 2. Run Services Locally with Maven

#### User Service
```bash
cd user-service
mvn spring-boot:run
```
Visit: http://localhost:8081/user/hello

#### Order Service
```bash
cd order-service
mvn spring-boot:run
```
Visit: http://localhost:8082/order/hello

### 3. Build and Run with Docker

```bash
# Build and start all services
docker-compose up --build

# To run in detached mode
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## CI/CD Pipeline

The project includes a GitHub Actions workflow (`.github/workflows/ci.yml`) that:

1. Triggers on pushes to `user-service/` or `order-service/`
2. Builds and tests only the changed service
3. In a real scenario, would build and push Docker images to a container registry

## API Endpoints

### User Service
- `GET /user/hello` - Returns a greeting from the User Service

### Order Service
- `GET /order/hello` - Returns a greeting from the Order Service

## Development

### Building the Project

```bash
# Build all services
mvn clean package

# Build specific service
cd user-service
mvn clean package
```

### Running Tests

```bash
# Run tests for all services
mvn test

# Run tests for specific service
cd user-service
mvn test
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.