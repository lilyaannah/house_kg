package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.Heating;

import java.util.List;

public interface HeatingService {
    Heating getHeatingById(Integer id);
    List<String> getDropDownList();
}
