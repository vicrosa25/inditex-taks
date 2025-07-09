package rosa.victor.prueba.infrastructure.out.persistence.mapper;

import rosa.victor.prueba.infrastructure.in.rest.dto.PriceDto;
import rosa.victor.prueba.infrastructure.out.persistence.entity.JpaPriceEntity;
import rosa.victor.prueba.model.Price;

final public class PriceMapper {

    private PriceMapper() {}

    static public Price toDomainModel(JpaPriceEntity priceEntity) {
        return new Price(
                priceEntity.getBrandId(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getPriceList(),
                priceEntity.getProductId(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurr()
        );
    }

    static public PriceDto toPriceDto(Price price) {
        return new PriceDto(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.price(),
                String.valueOf(price.curr())
        );
    }


}
