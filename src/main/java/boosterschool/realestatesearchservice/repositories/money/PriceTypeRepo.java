package boosterschool.realestatesearchservice.repositories.money;

import boosterschool.realestatesearchservice.models.money.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTypeRepo extends JpaRepository<PriceType, Integer> {
}
