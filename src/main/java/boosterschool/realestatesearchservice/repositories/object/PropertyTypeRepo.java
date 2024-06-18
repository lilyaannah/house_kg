package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepo extends JpaRepository<PropertyType, Integer> {
    PropertyType findPropertyTypeIdByTypeName(String name);
}
