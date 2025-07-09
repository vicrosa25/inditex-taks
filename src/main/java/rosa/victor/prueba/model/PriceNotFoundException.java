package rosa.victor.prueba.model;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Integer brandId, Integer productId, LocalDateTime applicationDate) {
      super("No price found for the given criteria: " + brandId + ", " + productId + ", " + applicationDate);
    }
}
