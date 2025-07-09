package rosa.victor.prueba.infrastructure.in.rest.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import rosa.victor.prueba.model.PriceNotFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(request.getRequestURI()).thenReturn("/test/uri");
    }

    @Test
    void handleSQLException_ShouldReturnInternalServerError() {
        // Given
        SQLException sqlException = new SQLException("Database connection error");

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleSQLException(sqlException, request);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Database error occurred: Database connection error", errorResponse.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse.getStatus());
        assertEquals("/test/uri", errorResponse.getPath());
        assertNotNull(errorResponse.getTimestamp());
    }

    @Test
    void handleDataAccessException_ShouldReturnInternalServerError() {
        // Given
        DataAccessException dataAccessException = new DataAccessException("Data access error") {};

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleSQLException(dataAccessException, request);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Database error occurred: Data access error", errorResponse.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse.getStatus());
        assertEquals("/test/uri", errorResponse.getPath());
        assertNotNull(errorResponse.getTimestamp());
    }



    @Test
    void handleMethodArgumentNotValidException_ShouldReturnNotFound() {
        // Given
        when(methodArgumentNotValidException.getMessage()).thenReturn("Validation failed");
        when(methodArgumentNotValidException.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

        FieldError fieldError1 = new FieldError("objectName", "field1", "Field1 error message");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Field2 error message");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleException(methodArgumentNotValidException, request);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("Validation failed", errorResponse.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getStatus());
        assertEquals("/test/uri", errorResponse.getPath());
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(2, errorResponse.getErrors().size());
        assertEquals("Field1 error message", errorResponse.getErrors().get("field1"));
        assertEquals("Field2 error message", errorResponse.getErrors().get("field2"));
    }

    @Test
    void handlePriceNotFoundException_ShouldReturnNotFound() {
        // Given
        Integer brandId = 1;
        Integer productId = 35455;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        PriceNotFoundException priceNotFoundException = new PriceNotFoundException(brandId, productId, applicationDate);

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleException(priceNotFoundException);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertNotNull(errorResponse);
        assertEquals("No price found for the given criteria: 1, 35455, 2020-06-14T10:00", errorResponse.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus());
        assertEquals("/prices", errorResponse.getPath());
        assertNotNull(errorResponse.getTimestamp());
    }
}
