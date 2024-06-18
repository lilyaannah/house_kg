package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepo extends JpaRepository<Condition, Integer> {
}
