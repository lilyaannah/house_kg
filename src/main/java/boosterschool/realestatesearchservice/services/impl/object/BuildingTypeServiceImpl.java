package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.BuildingType;
import boosterschool.realestatesearchservice.repositories.object.BuildingTypeRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.BuildingTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingTypeServiceImpl implements  BuildingTypeService {
    private final BuildingTypeRepo buildingTypeRepo;

    @Override
    public List<String> getDropDownList(){
        List<BuildingType> buildingTypeList = buildingTypeRepo.findAll();
        return buildingTypeList.stream()
                .filter(BuildingType::isActive) // Фильтрация по полю active
                .map(BuildingType::getBuildingType) // Преобразование в строку
                .collect(Collectors.toList());
    }

    @Override
    public BuildingType getBuildingTypeById(Integer id) {
        return buildingTypeRepo.getReferenceById(id);
    }
}
