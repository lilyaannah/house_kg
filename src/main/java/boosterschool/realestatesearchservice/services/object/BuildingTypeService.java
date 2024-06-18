package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.BuildingType;

import java.util.List;

public interface BuildingTypeService {
    BuildingType getBuildingTypeById(Integer id);
    List<String> getDropDownList();
}
