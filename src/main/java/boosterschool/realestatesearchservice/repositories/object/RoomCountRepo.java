package boosterschool.realestatesearchservice.repositories.object;

import boosterschool.realestatesearchservice.models.object.RoomCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCountRepo extends JpaRepository<RoomCount, Integer> {
}
