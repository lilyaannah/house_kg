package boosterschool.realestatesearchservice.services.impl.location;

import boosterschool.realestatesearchservice.dto.LocationDto;
import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.location.LocationType;
import boosterschool.realestatesearchservice.repositories.location.LocationRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.location.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements  LocationService {
    private final LocationRepo locationRepo;
    @Override
    public List<String> getDropDownList() {
        List<Location> locationList=locationRepo.findAll();
        return locationList.stream()
                .filter(Location::isActive)
                .map(Location::getLocationName)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Location> findByLocationNameIgnoreCaseAndLocationType_LocationTypeName(String locationName, String locationTypeName) {
        return locationRepo.findByLocationNameIgnoreCaseAndLocationType_LocationType(locationName, locationTypeName);
    }

    @Override
    public Location getLocationById(Integer id) {
        return locationRepo.getReferenceById(id);
    }


}
