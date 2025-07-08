package rosa.victor.pruebainditex.adapter.out.persistence.mapper;

import rosa.victor.pruebainditex.adapter.in.rest.dto.PriceDto;
import rosa.victor.pruebainditex.adapter.out.persistence.entity.PriceEntity;
import rosa.victor.pruebainditex.model.Price;

final public class PriceMapper {

    private PriceMapper() {}

    static public PriceEntity toEntity(Price price) {
        PriceEntity priceEntity = new PriceEntity();

        priceEntity.setBrandId(price.brandId());
        priceEntity.setStartDate(price.startDate());
        priceEntity.setEndDate(price.endDate());
        priceEntity.setPriceList(price.priceList());
        priceEntity.setProductId(price.productId());
        priceEntity.setPriority(price.priority());
        priceEntity.setPrice(price.price());
        priceEntity.setCurr(price.curr());

        return priceEntity;
    }

    static public Price toModel(PriceEntity priceEntity) {
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
        PriceDto dto = new PriceDto();
        dto.setProductId(price.productId());
        dto.setBrandId(price.brandId());
        dto.setPriceList(price.priceList());
        dto.setStartDate(price.startDate());
        dto.setEndDate(price.endDate());
        dto.setPrice(price.price());
        dto.setCurr(String.valueOf(price.curr()));

        return dto;
    }


}
