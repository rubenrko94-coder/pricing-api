# Technical Test — Applicable Pricing Service

> Requirements: **Java 21** and **Maven 3.9+**

## Run the application

```
mvn clean verify
mvn spring-boot:run
```

### How to execute the API
Swagger is provided to send requests, and the OpenAPI document (JSON) that can be imported into Postman:
- UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### H2 database
- `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:pricingdb`
- User: `sa` / Password: (empty)

## Endpoint

`GET /api/v1/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1`

## Tests

Includes the 5 requested integration tests. To run them, open each test class in your IDE and click the run button.
