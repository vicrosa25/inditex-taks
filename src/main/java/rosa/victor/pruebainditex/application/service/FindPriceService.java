package rosa.victor.pruebainditex.application.service;

import lombok.AllArgsConstructor;
import rosa.victor.pruebainditex.application.port.in.FindPriceUseCase;
import rosa.victor.pruebainditex.application.port.out.persistence.PriceRepository;
import rosa.victor.pruebainditex.model.Price;

import java.time.LocalDateTime;

@AllArgsConstructor
public class FindPriceService implements FindPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceRepository.findPrice(applicationDate, productId, brandId);
    }
}
