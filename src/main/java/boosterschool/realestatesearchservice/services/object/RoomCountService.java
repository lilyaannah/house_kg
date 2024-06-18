package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.RoomCount;

import java.util.List;

public interface RoomCountService {
    RoomCount getRoomCountById(Integer id);
    List<Integer> getDropDownList();
}
