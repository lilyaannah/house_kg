package boosterschool.realestatesearchservice.repositories.money;

import boosterschool.realestatesearchservice.models.money.ExchangeOption;
import boosterschool.realestatesearchservice.models.object.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeOptionRepo extends JpaRepository<ExchangeOption, Integer> {
}
