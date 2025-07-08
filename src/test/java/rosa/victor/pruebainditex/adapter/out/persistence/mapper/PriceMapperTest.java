package rosa.victor.pruebainditex.adapter.out.persistence.mapper;

import org.junit.jupiter.api.Test;
import rosa.victor.pruebainditex.adapter.out.persistence.entity.PriceEntity;
import rosa.victor.pruebainditex.model.Currency;
import rosa.victor.pruebainditex.model.Price;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PriceMapperTest {

    @Test
    void testToEntity_ShouldMapAllFields() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Price price = new Price(
                1,
                startDate,
                endDate,
                1,
                35455,
                0,
                35.50,
                Currency.EUR
        );

        // When
        PriceEntity result = PriceMapper.toEntity(price);

        // Then
        assertNotNull(result);
        assertEquals(price.brandId(), result.getBrandId());
        assertEquals(price.startDate(), result.getStartDate());
        assertEquals(price.endDate(), result.getEndDate());
        assertEquals(price.priceList(), result.getPriceList());
        assertEquals(price.productId(), result.getProductId());
        assertEquals(price.priority(), result.getPriority());
        assertEquals(price.price(), result.getPrice());
        assertEquals(price.curr(), result.getCurr());
    }

    @Test
    void testToModel_ShouldMapAllFields() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1);
        priceEntity.setBrandId(1);
        priceEntity.setStartDate(startDate);
        priceEntity.setEndDate(endDate);
        priceEntity.setPriceList(1);
        priceEntity.setProductId(35455);
        priceEntity.setPriority(0);
        priceEntity.setPrice(35.50);
        priceEntity.setCurr(Currency.EUR);

        // When
        Price result = PriceMapper.toModel(priceEntity);

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
}