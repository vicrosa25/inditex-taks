package rosa.victor.prueba.application.port.out.persistence;

import rosa.victor.prueba.model.Price;

import java.util.List;

public interface PriceRepository {
    List<Price> findAllPrices();
}
