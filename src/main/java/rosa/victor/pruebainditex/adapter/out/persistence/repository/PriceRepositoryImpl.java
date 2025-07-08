package rosa.victor.pruebainditex.adapter.out.persistence.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import rosa.victor.pruebainditex.adapter.out.persistence.entity.PriceEntity;
import rosa.victor.pruebainditex.adapter.out.persistence.mapper.PriceEntityRowMapper;
import rosa.victor.pruebainditex.adapter.out.persistence.mapper.PriceMapper;
import rosa.victor.pruebainditex.application.port.out.persistence.PriceRepository;
import rosa.victor.pruebainditex.model.Price;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        String sql = """
                    SELECT * FROM PRICES
                    WHERE  START_DATE <= ? AND END_DATE >= ? AND PRODUCT_ID = ? AND BRAND_ID = ?
                    ORDER BY PRIORITY DESC
                    LIMIT 1;
               """;

        PriceEntity priceEntity = jdbcTemplate.queryForObject(
                sql,
                new PriceEntityRowMapper(),
                applicationDate,
                applicationDate,
                productId,
                brandId
        );

        return Objects.isNull(priceEntity) ? null : PriceMapper.toModel(priceEntity);

    }
}
