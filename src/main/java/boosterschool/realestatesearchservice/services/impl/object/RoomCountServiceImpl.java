package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.RoomCount;
import boosterschool.realestatesearchservice.repositories.object.RoomCountRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.RoomCountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomCountServiceImpl implements RoomCountService {
    private final RoomCountRepo roomCountRepo;

    @Override
    public List<Integer> getDropDownList() {
        List<RoomCount> roomCountList=roomCountRepo.findAll();
        return roomCountList.stream()
                .filter(RoomCount::isActive)
                .map(RoomCount::getRoomCount)
                .collect(Collectors.toList());
    }

    @Override
    public RoomCount getRoomCountById(Integer id) {
        return roomCountRepo.getReferenceById(id);
    }
}
