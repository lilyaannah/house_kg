package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.BuildingType;
import boosterschool.realestatesearchservice.models.object.Condition;
import boosterschool.realestatesearchservice.repositories.object.ConditionRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.ConditionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConditionServiceImpl implements  ConditionService {
    private final ConditionRepo conditionRepo;
    @Override
    public List<String> getDropDownList(){
        List<Condition> conditionList=conditionRepo.findAll();
        return conditionList.stream()
                .filter(Condition::isActive) // Фильтрация по полю active
                .map(Condition::getConditionType) // Преобразование в строку
                .collect(Collectors.toList());
    }

    @Override
    public Condition getConditionById(Integer id) {

        return conditionRepo.getReferenceById(id);
    }
}
