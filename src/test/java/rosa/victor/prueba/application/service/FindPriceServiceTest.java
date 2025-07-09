package rosa.victor.prueba.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rosa.victor.prueba.application.port.out.persistence.PriceRepository;
import rosa.victor.prueba.model.Currency;
import rosa.victor.prueba.model.Price;
import rosa.victor.prueba.model.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FindPriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private FindPriceService findPriceService;

    private List<Price> prices;


    @BeforeEach
    public void setUp() {
        prices = List.of(
                new Price(1,
                        LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        1 , 35455, 0, 35.50, Currency.EUR),
                new Price(1,
                        LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                        LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                        2, 35455, 1, 25.45, Currency.EUR),
                new Price(1,
                        LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                        LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                        3, 35455, 1, 30.50, Currency.EUR),
                new Price(1,
                        LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        4, 35455, 1, 38.95, Currency.EUR));
    }

    @Test
    void testFindPrice_WithSpecific_2020_06_14_10_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        int productId = 35455;
        int brandId = 1;

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When
        Price result = findPriceService.findPrice(applicationDate, productId, brandId);

        // Then
        assertEquals(35.50, result.price());
        assertEquals(1, result.priceList());
        assertEquals(0, result.priority());
    }

    @Test
    void testFindPrice_WithSpecific_2020_06_14_16_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        int productId = 35455;
        int brandId = 1;

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When
        Price result = findPriceService.findPrice(applicationDate, productId, brandId);

        // Then
        assertEquals(25.45, result.price());
        assertEquals(2, result.priceList());
        assertEquals(1, result.priority());
    }

    @Test
    void testFindPrice_WithSpecific_2020_06_14_21_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When
        Price result = findPriceService.findPrice(applicationDate, productId, brandId);

        // Then
        assertEquals(35.50, result.price());
        assertEquals(1, result.priceList());
        assertEquals(0, result.priority());
    }

    @Test
    void testFindPrice_WithSpecific_2020_06_15_10_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When
        Price result = findPriceService.findPrice(applicationDate, productId, brandId);

        // Then
        assertEquals(30.50, result.price());
        assertEquals(3, result.priceList());
        assertEquals(1, result.priority());
    }

    @Test
    void testFindPrice_WithSpecific_2020_06_16_21_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When
        Price result = findPriceService.findPrice(applicationDate, productId, brandId);

        // Then
        assertEquals(38.95, result.price());
        assertEquals(4, result.priceList());
        assertEquals(1, result.priority());
    }

    @Test
    void testFindPrice_NotFound() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 99999; // Non-existent product ID
        Integer brandId = 1;

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When & Then
        PriceNotFoundException exception = assertThrows(
            PriceNotFoundException.class,
            () -> findPriceService.findPrice(applicationDate, productId, brandId)
        );

        // Verify exception message contains relevant information
        String exceptionMessage = exception.getMessage();
        assertTrue(exceptionMessage.contains(brandId.toString()));
        assertTrue(exceptionMessage.contains(productId.toString()));
        assertTrue(exceptionMessage.contains(applicationDate.toString()));
    }

    @Test
    void testFindPrice_NotFoundDueToApplicationDate() {
        // Given
        // Date before any valid price range
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 13, 10, 0, 0);
        Integer productId = 35455; // Valid product ID
        Integer brandId = 1; // Valid brand ID

        given(priceRepository.findAllPrices()).willReturn(prices);

        // When & Then
        PriceNotFoundException exception = assertThrows(
            PriceNotFoundException.class,
            () -> findPriceService.findPrice(applicationDate, productId, brandId)
        );

        // Verify exception message contains relevant information
        String exceptionMessage = exception.getMessage();
        assertTrue(exceptionMessage.contains(brandId.toString()));
        assertTrue(exceptionMessage.contains(productId.toString()));
        assertTrue(exceptionMessage.contains(applicationDate.toString()));
    }
}
