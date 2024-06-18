package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.Heating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatingRepo extends JpaRepository<Heating, Integer> {
}
