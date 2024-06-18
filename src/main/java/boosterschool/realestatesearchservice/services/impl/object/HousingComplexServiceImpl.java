package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.HousingComplex;
import boosterschool.realestatesearchservice.repositories.object.HousingComplexRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.HousingComplexService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HousingComplexServiceImpl implements HousingComplexService {
    private final HousingComplexRepo housingComplexRepo;

    @Override
    public List<String> getDropDownList(){
        List<HousingComplex> housingComplexList=housingComplexRepo.findAll();
        return housingComplexList.stream()
                .filter(HousingComplex::isActive)
                .map(HousingComplex::getHousingComplexName)
                .collect(Collectors.toList());
    }

    @Override
    public HousingComplex getHousingComplexById(Integer id) {
        return housingComplexRepo.getReferenceById(id);
    }
}
