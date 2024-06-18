package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.PropertyType;

import java.util.List;

public interface PropertyTypeService {
    PropertyType getPropertyTypeById(Integer id);
    List<String> getDropDownList();
}
