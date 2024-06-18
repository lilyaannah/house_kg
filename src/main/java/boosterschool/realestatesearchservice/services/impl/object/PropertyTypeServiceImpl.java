package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.PropertyType;
import boosterschool.realestatesearchservice.repositories.object.PropertyTypeRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.PropertyTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PropertyTypeServiceImpl implements PropertyTypeService {
    private final PropertyTypeRepo propertyTypeRepo;

    @Override
    public List<String> getDropDownList() {
        List<PropertyType> propertyTypeList=propertyTypeRepo.findAll();
        return propertyTypeList.stream()
                .filter(PropertyType::isActive)
                .map(PropertyType::getTypeName)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyType getPropertyTypeById(Integer id) {
        return propertyTypeRepo.getReferenceById(id);
    }
}
