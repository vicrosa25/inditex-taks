package rosa.victor.pruebainditex.adapter.out.persistence.mapper;

import org.springframework.jdbc.core.RowMapper;
import rosa.victor.pruebainditex.adapter.out.persistence.entity.PriceEntity;
import rosa.victor.pruebainditex.model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceEntityRowMapper implements RowMapper<PriceEntity> {

    @Override
    public PriceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        var priceEntity = new PriceEntity();
        priceEntity.setId(rs.getInt("ID"));
        priceEntity.setBrandId(rs.getInt("BRAND_ID"));
        priceEntity.setStartDate(rs.getTimestamp("START_DATE").toLocalDateTime());
        priceEntity.setEndDate(rs.getTimestamp("END_DATE").toLocalDateTime());
        priceEntity.setPriceList(rs.getInt("PRICE_LIST"));
        priceEntity.setProductId(rs.getInt("PRODUCT_ID"));
        priceEntity.setPriority(rs.getInt("PRIORITY"));
        priceEntity.setPrice(rs.getDouble("PRICE"));
        priceEntity.setCurr(Currency.valueOf(rs.getString("CURR")));

        return priceEntity;
    }
}
