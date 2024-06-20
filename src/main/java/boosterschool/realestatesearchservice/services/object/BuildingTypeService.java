package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.BuildingType;

import java.util.List;
import java.util.Optional;

public interface BuildingTypeService {
    BuildingType getBuildingTypeById(Integer id);
    List<String> getDropDownList();
}
