package boosterschool.realestatesearchservice.repositories.money;

import boosterschool.realestatesearchservice.models.money.InstallmentPlan;
import boosterschool.realestatesearchservice.models.object.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentPlanRepo extends JpaRepository<InstallmentPlan, Integer> {
}
