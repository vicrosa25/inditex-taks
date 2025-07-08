package rosa.victor.pruebainditex.application.port.in;

import rosa.victor.pruebainditex.model.Price;
import java.time.LocalDateTime;

public interface FindPriceUseCase {

    Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);

}
