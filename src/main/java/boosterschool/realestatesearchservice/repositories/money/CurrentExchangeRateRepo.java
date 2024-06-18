package boosterschool.realestatesearchservice.repositories.money;

import boosterschool.realestatesearchservice.models.money.CurrentExchangeRate;
import boosterschool.realestatesearchservice.models.object.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentExchangeRateRepo extends JpaRepository<CurrentExchangeRate, Integer> {
    Optional<CurrentExchangeRate> findTopByOrderByStartDateDesc();
}
