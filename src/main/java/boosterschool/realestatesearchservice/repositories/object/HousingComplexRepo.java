package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.HousingComplex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingComplexRepo extends JpaRepository<HousingComplex, Integer> {
}
