package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.HousingComplex;

import java.util.List;

public interface HousingComplexService {
    HousingComplex getHousingComplexById(Integer id);
    List<String> getDropDownList();
}
