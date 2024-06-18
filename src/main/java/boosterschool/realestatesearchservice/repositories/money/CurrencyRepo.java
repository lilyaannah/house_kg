package boosterschool.realestatesearchservice.repositories.money;

import boosterschool.realestatesearchservice.models.money.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Integer> {
}
