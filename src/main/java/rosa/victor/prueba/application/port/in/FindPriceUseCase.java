package rosa.victor.prueba.application.port.in;

import rosa.victor.prueba.model.Price;
import java.time.LocalDateTime;

public interface FindPriceUseCase {

    Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);

}
