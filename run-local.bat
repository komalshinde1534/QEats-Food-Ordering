@echo off
echo Start Docker infrastructure first: docker compose up -d
echo Then open two terminals and run:
echo mvn spring-boot:run -pl restaurant-service
echo mvn spring-boot:run -pl order-service
