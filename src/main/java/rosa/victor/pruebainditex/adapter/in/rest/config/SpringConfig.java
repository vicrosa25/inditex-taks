package rosa.victor.pruebainditex.adapter.in.rest.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rosa.victor.pruebainditex.application.port.in.FindPriceUseCase;
import rosa.victor.pruebainditex.application.port.out.persistence.PriceRepository;
import rosa.victor.pruebainditex.application.service.FindPriceService;

@AllArgsConstructor
@Configuration
public class SpringConfig {

    private final PriceRepository priceRepository;

    @Bean
    public FindPriceUseCase findPriceUseCase() {
        return new FindPriceService(priceRepository);
    }
}
