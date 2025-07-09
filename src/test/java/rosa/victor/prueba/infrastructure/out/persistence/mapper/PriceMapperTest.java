package rosa.victor.prueba.infrastructure.out.persistence.mapper;

import org.junit.jupiter.api.Test;
import rosa.victor.prueba.infrastructure.in.rest.dto.PriceDto;
import rosa.victor.prueba.infrastructure.out.persistence.entity.JpaPriceEntity;
import rosa.victor.prueba.model.Currency;
import rosa.victor.prueba.model.Price;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PriceMapperTest {


    @Test
    void testToModel_ShouldMapAllFields() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        JpaPriceEntity priceEntity =
                new JpaPriceEntity(1, 1, startDate, endDate, 1, 35455, 0, 35.50, Currency.EUR);

        // When
        Price result = PriceMapper.toDomainModel(priceEntity);

        // Then
        assertNotNull(result);
        assertEquals(priceEntity.getBrandId(), result.brandId());
        assertEquals(priceEntity.getStartDate(), result.startDate());
        assertEquals(priceEntity.getEndDate(), result.endDate());
        assertEquals(priceEntity.getPriceList(), result.priceList());
        assertEquals(priceEntity.getProductId(), result.productId());
        assertEquals(priceEntity.getPriority(), result.priority());
        assertEquals(priceEntity.getPrice(), result.price());
        assertEquals(priceEntity.getCurr(), result.curr());
    }

    @Test
    void testToPriceDto_ShouldMapAllFields() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Price price = new Price(1, startDate, endDate, 1, 35455, 0, 35.50, Currency.EUR);

        // When
        PriceDto result = PriceMapper.toPriceDto(price);

        // Then
        assertNotNull(result);
        assertEquals(price.productId(), result.productId());
        assertEquals(price.brandId(), result.brandId());
        assertEquals(price.priceList(), result.priceList());
        assertEquals(price.startDate(), result.startDate());
        assertEquals(price.endDate(), result.endDate());
        assertEquals(price.price(), result.price());
        assertEquals(String.valueOf(price.curr()), result.curr());
    }
}