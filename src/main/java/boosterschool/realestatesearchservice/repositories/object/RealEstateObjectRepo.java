package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.object.RealEstateObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RealEstateObjectRepo extends JpaRepository<RealEstateObject, Integer> , JpaSpecificationExecutor<RealEstateObject> {
    @Query(value = "Select l3.location_name from locations l3 where id =(\n" +
            "Select l2.location_id from locations l2 where id =(\n" +
            "select  l1.location_id from locations l1\n" +
            "\twhere upper(l1.location_name) =upper(:locationName)));",nativeQuery = true)
    String getRegion(String locationName);

    @Query(value = "Select l2.location_name from locations l2 where id =(\n" +
            "select  l1.location_id from locations l1\n" +
            "\twhere upper(l1.location_name) =upper(:locationName))",nativeQuery = true)
    String getCity(String locationName);
}
