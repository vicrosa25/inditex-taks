package rosa.victor.prueba.application.service;

import lombok.AllArgsConstructor;
import rosa.victor.prueba.application.port.in.FindPriceUseCase;
import rosa.victor.prueba.application.port.out.persistence.PriceRepository;
import rosa.victor.prueba.model.Price;
import rosa.victor.prueba.model.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

@AllArgsConstructor
public class FindPriceService implements FindPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceRepository.findAllPrices().stream()
                .filter(price -> price.productId().equals(productId))
                .filter(price -> price.brandId().equals(brandId))
                .filter(isWithinDateRange(applicationDate))
                .max(Comparator.comparing(Price::priority))
                .orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate));
    }

    private Predicate<Price> isWithinDateRange(LocalDateTime date) {
        return price -> (date.isAfter(price.startDate()) || date.isEqual(price.startDate()))
                && (date.isBefore(price.endDate()) || date.isEqual(price.endDate()));
    }

}
