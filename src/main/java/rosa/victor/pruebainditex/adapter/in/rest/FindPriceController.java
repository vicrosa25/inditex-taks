package rosa.victor.pruebainditex.adapter.in.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rosa.victor.pruebainditex.adapter.in.rest.dto.PriceDto;
import rosa.victor.pruebainditex.adapter.in.rest.dto.RequestParameters;
import rosa.victor.pruebainditex.adapter.out.persistence.mapper.PriceMapper;
import rosa.victor.pruebainditex.application.port.in.FindPriceUseCase;

@RestController
@AllArgsConstructor
@Tag(name = "Prices", description = "Find prices for a given product and brand")
@RequestMapping("/prices")
public class FindPriceController {

    private final FindPriceUseCase findPriceUseCase;

    @PostMapping
    public ResponseEntity<PriceDto> findPrice(@Valid RequestParameters rp) {
        return new ResponseEntity<>(PriceMapper.toPriceDto(
                findPriceUseCase.findPrice(rp.applicationDate(), rp.productId(), rp.brandId())), HttpStatus.OK
        );
    }
}
