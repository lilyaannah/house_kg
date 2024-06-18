package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingTypeRepo extends JpaRepository<BuildingType, Integer> {

}
