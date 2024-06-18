package boosterschool.realestatesearchservice.repositories.location;

import boosterschool.realestatesearchservice.models.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    Optional<Location> findByLocationNameIgnoreCaseAndLocationType_LocationType(String locationName, String locationTypeName);
}
