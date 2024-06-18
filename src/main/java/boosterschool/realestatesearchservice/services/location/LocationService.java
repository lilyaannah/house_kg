package boosterschool.realestatesearchservice.services.location;

import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.location.LocationType;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    Location getLocationById(Integer id);

    List<String> getDropDownList();

    Optional<Location> findByLocationNameIgnoreCaseAndLocationType_LocationTypeName(String locationName, String locationTypeName);
}
