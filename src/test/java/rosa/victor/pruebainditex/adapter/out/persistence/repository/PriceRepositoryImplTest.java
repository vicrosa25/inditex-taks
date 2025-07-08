package rosa.victor.pruebainditex.adapter.out.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import rosa.victor.pruebainditex.model.Currency;
import rosa.victor.pruebainditex.model.Price;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJdbcTest
class PriceRepositoryImplTest {

    private final PriceRepositoryImpl priceRepositoryImpl;

    @Autowired
    public PriceRepositoryImplTest(JdbcTemplate jdbcTemplate) {
        this.priceRepositoryImpl = new PriceRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testFindPrice_WithSpecific_2020_06_14_10_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        Price result = priceRepositoryImpl.findPrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(brandId, result.brandId());
        assertEquals(productId, result.productId());
        assertEquals(1, result.priceList());
        assertEquals(0, result.priority());
        assertEquals(35.50, result.price());
        assertEquals(Currency.EUR, result.curr());
        assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), result.endDate());
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_14_16_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        Price result = priceRepositoryImpl.findPrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(brandId, result.brandId());
        assertEquals(productId, result.productId());
        assertEquals(2, result.priceList());
        assertEquals(1, result.priority());
        assertEquals(25.45, result.price());
        assertEquals(Currency.EUR, result.curr());
        assertEquals(LocalDateTime.of(2020, 6, 14, 15, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2020, 6, 14, 18, 30, 0), result.endDate());
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_14_21_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        Price result = priceRepositoryImpl.findPrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(brandId, result.brandId());
        assertEquals(productId, result.productId());
        assertEquals(1, result.priceList());
        assertEquals(0, result.priority());
        assertEquals(35.50, result.price());
        assertEquals(Currency.EUR, result.curr());
        assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), result.endDate());
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_15_10_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        Price result = priceRepositoryImpl.findPrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(brandId, result.brandId());
        assertEquals(productId, result.productId());
        assertEquals(3, result.priceList());
        assertEquals(1, result.priority());
        assertEquals(30.50, result.price());
        assertEquals(Currency.EUR, result.curr());
        assertEquals(LocalDateTime.of(2020, 6, 15, 0, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2020, 6, 15, 11, 0, 0), result.endDate());
    }

    @Test
    void testFindPrice_ApplicationDate_2020_06_16_21_00_00() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        // When
        Price result = priceRepositoryImpl.findPrice(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(brandId, result.brandId());
        assertEquals(productId, result.productId());
        assertEquals(4, result.priceList());
        assertEquals(1, result.priority());
        assertEquals(38.95, result.price());
        assertEquals(Currency.EUR, result.curr());
        assertEquals(LocalDateTime.of(2020, 6, 15, 16, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), result.endDate());
    }
}
