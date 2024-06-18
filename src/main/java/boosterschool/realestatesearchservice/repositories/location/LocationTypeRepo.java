package boosterschool.realestatesearchservice.repositories.location;

import boosterschool.realestatesearchservice.models.location.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationTypeRepo extends JpaRepository<LocationType, Integer> {
}
