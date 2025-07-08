package rosa.victor.pruebainditex.application.port.out.persistence;

import rosa.victor.pruebainditex.model.Price;

import java.time.LocalDateTime;

public interface PriceRepository {
    Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
