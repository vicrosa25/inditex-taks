# Price API

## Description
This project is a Spring Boot application that provides a REST API for retrieving pricing information for products based on brand, product ID, and application date. The application follows a hexagonal architecture pattern (also known as ports and adapters) to ensure separation of concerns and maintainability.

## Technologies Used
- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Lombok
- SpringDoc OpenAPI for API documentation

## Architecture
The application follows a hexagonal architecture with the following components:

### Core Layers
- **Domain Layer**: Contains the business entities and logic (Price model)
  - Package: `rosa.victor.prueba.model`
- **Application Layer**: Contains the use cases and ports (interfaces)
  - Package: `rosa.victor.prueba.application`
  - **Ports**: Interfaces that define the contracts for the adapters
    - **In**: Interfaces for incoming adapters (e.g., REST controllers)
      - Package: `rosa.victor.prueba.application.port.in`
    - **Out**: Interfaces for outgoing adapters (e.g., repositories)
      - Package: `rosa.victor.prueba.application.port.out`

### Adapters
- **In Adapters**: Implementations of the input ports
  - **REST**: REST controllers that handle HTTP requests
    - Package: `rosa.victor.prueba.infrastructure.in.rest`
- **Out Adapters**: Implementations of the output ports
  - **Persistence**: Repository implementations for data access
    - Package: `rosa.victor.prueba.infrastructure.out.persistence`

## API Documentation
The API is documented using SpringDoc OpenAPI. When the application is running, you can access the API documentation at:
```
http://localhost:8080/swagger-ui.html
```

### Endpoints
- **POST /prices**: Find the applicable price for a product and brand at a specific date
  - **Request Parameters**:
    - `applicationDate`: The date and time for which to find the price (ISO format)
    - `productId`: The product identifier
    - `brandId`: The brand identifier
  - **Response**: A PriceDto containing the price information with the following fields:
    - `productId`: The product identifier
    - `brandId`: The brand identifier
    - `priceList`: The price list identifier
    - `startDate`: The start date of price validity
    - `endDate`: The end date of price validity
    - `price`: The actual price value
    - `curr`: Currency code (e.g., EUR)

## Exception Handling
The application implements a global exception handling mechanism using Spring's `@ControllerAdvice` to provide consistent error responses across the API. All error responses follow a standardized format:

```json
{
  "message": "Description of the error",
  "status": 404,
  "timestamp": "2023-09-15T10:15:30.123Z",
  "errors": {},
  "path": "/prices"
}
```

### Handled Exceptions
The application handles the following types of exceptions:

1. **Database Exceptions** (SQLException, DataAccessException)
   - Status: 500 Internal Server Error
   - Occurs when there are issues with database operations

2. **No Price Found** (EmptyResultDataAccessException)
   - Status: 404 Not Found
   - Occurs when no price is found for the given parameters

3. **Validation Errors** (MethodArgumentNotValidException)
   - Status: 400 Bad Request
   - Occurs when request parameters fail validation
   - The `errors` field will contain field-specific validation errors

## Database and Data Access
The application uses an H2 in-memory database with the following schema:

### PRICES Table
- `id`: Primary key
- `brand_id`: Brand identifier
- `start_date`: Start date of price validity
- `end_date`: End date of price validity
- `price_list`: Price list identifier
- `product_id`: Product identifier
- `priority`: Priority of the price (used to resolve conflicts)
- `price`: The actual price value
- `curr`: Currency code (e.g., EUR)

Sample data is loaded from `data.sql` when the application starts.

### Data Access Strategy
The application uses a combination of Spring Data JPA and in-memory filtering:

1. The `PriceRepository` interface defines a method to retrieve all prices.
2. The `FindPriceService` applies filters to:
   - Match the requested product ID
   - Match the requested brand ID
   - Ensure the application date falls within the price's validity period
   - Select the price with the highest priority when multiple prices match

This approach allows for flexible filtering and sorting of prices based on multiple criteria.

## Building and Running the Application

### Prerequisites
- Java 21 or higher
- Maven

### Build
```bash
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```
The application will start on port 8080 by default.

## Testing
The application includes both integration tests and unit tests to ensure the functionality works as expected. You can run all tests with:
```bash
mvn test
```

### Integration Tests
Integration tests are implemented in the controller layer to verify the end-to-end functionality of the API:

- **FindPriceControllerTest**: Tests the REST API endpoint with various date/time scenarios to ensure the correct prices are returned. These tests use `@SpringBootTest` and `MockMvc` to simulate HTTP requests and verify responses, testing the entire application stack including:
  - Request parameter validation
  - Controller logic
  - Service layer processing
  - Repository data access
  - Response mapping
  - Error handling

The integration tests cover:
- Five specific test cases with different date/time inputs to verify the price retrieval logic work correctly in various scenarios
- Error handling for non-existent prices (404 Not Found)
- Error handling for missing required parameters (400 Bad Request)
- Error handling for invalid parameter formats (400 Bad Request)

### Unit Tests
Unit tests are implemented for individual components to verify their functionality in isolation:

- **FindPriceServiceTest**: Tests the service layer logic
  - Uses Mockito to mock the repository
  - Tests five specific date/time scenarios to verify the correct price selection
  - Tests error handling for non-existent products
  - Tests error handling for dates outside valid ranges

- **PriceMapperTest**: Tests the mapping between domain models and persistence entities
  - Verifies correct mapping from domain model to entity
  - Verifies correct mapping from entity to the domain model

- **GlobalExceptionHandlerTest**: Tests the exception handling mechanism
  - Verifies correct handling of SQL exceptions
  - Verifies correct handling of data access exceptions
  - Verifies correct handling of empty result exceptions
  - Verifies correct handling of validation errors

The unit tests ensure that each component functions correctly in isolation, while the integration tests verify that all components work together as expected.

## Example Usage
To find the price for product ID 35455, brand ID 1, on June 14, 2020, at 10:00:00:

```bash
curl -X POST "http://localhost:8080/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1"
```

Response:
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "curr": "EUR"
}
```
