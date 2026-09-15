# QEats – Distributed Food Ordering Backend

A small interview-ready distributed food-ordering backend demonstrating **Java 21, Spring Boot, REST, MySQL, Redis, JSON serialization, JUnit 5 and Mockito**.

## Architecture
```text
Client
  |
  +--> Order Service :8082 ----REST/JSON----> Restaurant Service :8081
  |          |                                  |
  |          +--> Redis (restaurant cache)      +--> MySQL (restaurant DB)
  |          |
  |          +--> MySQL (order DB)
```

### Services
- **restaurant-service**: restaurant/menu CRUD-lite APIs, MySQL persistence.
- **order-service**: creates orders, validates menu items through restaurant-service, persists orders, and caches restaurant responses in Redis for 10 minutes.

## Requirements
- JDK 21+
- Maven 3.9+
- Docker Desktop

## Run infrastructure
```bash
docker compose up -d
```

## Run services
From project root:
```bash
mvn clean test
mvn spring-boot:run -pl restaurant-service
```
In another terminal:
```bash
mvn spring-boot:run -pl order-service
```

## APIs
### Restaurant
`GET http://localhost:8081/api/v1/restaurants/1`

`GET http://localhost:8081/api/v1/restaurants?city=Pune`

`POST http://localhost:8081/api/v1/restaurants`
```json
{
  "name":"Burger Point",
  "cuisine":"Fast Food",
  "city":"Pune",
  "available":true,
  "menu":["Veg Burger","Fries"]
}
```

### Order
`GET http://localhost:8082/api/v1/orders/restaurant/1`

`POST http://localhost:8082/api/v1/orders`
```json
{
  "restaurantId":1,
  "customerName":"Komal",
  "item":"Paneer Tikka",
  "quantity":2,
  "unitPrice":150
}
```

## What this demonstrates in an interview
1. **Distributed communication** – Order Service calls Restaurant Service over REST using JSON.
2. **Serialization** – Jackson converts `RestaurantDto` to/from JSON; the same JSON representation is stored in Redis.
3. **Caching** – cache-aside pattern with a 10-minute Redis TTL, reducing repeated calls to Restaurant Service.
4. **Persistence** – separate MySQL databases for the two services, avoiding a shared database.
5. **Unit testing** – JUnit 5 + Mockito tests cache hit, downstream call, order creation, calculation, and validation.
6. **Scalability** – services are independently deployable and stateless at application level; each can be horizontally scaled.
7. **Availability** – the services are separated so one domain can be scaled/restarted independently. Production systems should additionally add timeouts, retries with backoff, circuit breakers, health checks, service discovery and centralized observability.

## Suggested next interview upgrades
- Spring Cloud Gateway
- Resilience4j circuit breaker + timeout
- Kafka for asynchronous order events
- Docker images for both services
- Kubernetes deployment + readiness/liveness probes
- OpenTelemetry traceId/correlationId
- Testcontainers for integration tests
