package rosa.victor.prueba.model;

import java.time.LocalDateTime;

public record Price (
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        Double price,
        Currency curr
){}
