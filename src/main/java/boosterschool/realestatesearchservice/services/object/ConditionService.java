package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.Condition;

import java.util.List;

public interface ConditionService {
    Condition getConditionById(Integer id);
    List<String> getDropDownList();
}
