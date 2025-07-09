package rosa.victor.prueba.infrastructure.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rosa.victor.prueba.infrastructure.out.persistence.entity.JpaPriceEntity;

public interface JpaPriceRepository extends JpaRepository<JpaPriceEntity, Integer> {
}
