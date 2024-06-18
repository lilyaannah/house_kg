package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.Condition;
import boosterschool.realestatesearchservice.models.object.DealType;
import boosterschool.realestatesearchservice.repositories.object.DealTypeRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.DealTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DealTypeServiceImpl implements DealTypeService {
    private final DealTypeRepo dealTypeRepo;
    @Override
    public List<String> getDropDownList(){
        List<DealType> dealTypeList=dealTypeRepo.findAll();
        return dealTypeList.stream()
                .filter(DealType::isActive) // Фильтрация по полю active
                .map(DealType::getTypeName) // Преобразование в строку
                .collect(Collectors.toList());
    }

    @Override
    public DealType getDealTypeById(Integer id) {
        return dealTypeRepo.getReferenceById(id);
    }
}
