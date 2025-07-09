package rosa.victor.prueba.infrastructure.out.persistence.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import rosa.victor.prueba.infrastructure.out.persistence.mapper.PriceMapper;
import rosa.victor.prueba.application.port.out.persistence.PriceRepository;
import rosa.victor.prueba.model.Price;
import java.util.List;

@AllArgsConstructor
@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public List<Price> findAllPrices() {
        return jpaPriceRepository.findAll().stream()
                .map(PriceMapper::toDomainModel)
                .toList();
    }
}
