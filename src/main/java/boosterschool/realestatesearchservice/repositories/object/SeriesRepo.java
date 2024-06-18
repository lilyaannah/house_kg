package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.Condition;
import boosterschool.realestatesearchservice.models.object.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepo extends JpaRepository<Series, Integer> {
}
