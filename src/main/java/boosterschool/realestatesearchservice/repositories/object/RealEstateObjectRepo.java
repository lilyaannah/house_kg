package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.RealEstateObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RealEstateObjectRepo extends JpaRepository<RealEstateObject, Integer> {
    List<RealEstateObject> findAll(Specification<RealEstateObject> houseKgObjectParameters);
}
