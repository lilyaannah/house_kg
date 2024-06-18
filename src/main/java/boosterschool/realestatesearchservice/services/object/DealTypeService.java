package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.DealType;
import boosterschool.realestatesearchservice.repositories.object.DealTypeRepo;

import java.util.List;

public interface DealTypeService {
    DealType getDealTypeById(Integer id);
    List<String> getDropDownList();
}
