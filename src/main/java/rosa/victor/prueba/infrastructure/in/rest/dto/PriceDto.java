package rosa.victor.prueba.infrastructure.in.rest.dto;

import java.time.LocalDateTime;

public record PriceDto (
    Integer productId,
    Integer brandId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Double price,
    String curr
) {}
