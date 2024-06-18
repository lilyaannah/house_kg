package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.DealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealTypeRepo extends JpaRepository<DealType, Integer> {
    DealType findDealTypeIdByTypeName(String name);
}
