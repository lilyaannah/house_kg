package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.Heating;
import boosterschool.realestatesearchservice.repositories.object.HeatingRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.HeatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HeatingServiceImpl implements HeatingService {
    private final HeatingRepo heatingRepo;
    @Override
    public List<String> getDropDownList(){
        List <Heating> heatingList=heatingRepo.findAll();
        return heatingList.stream()
                .filter(Heating::isActive)
                .map(Heating::getHeatingType)
                .collect(Collectors.toList());
    }

    @Override
    public Heating getHeatingById(Integer id) {
        return heatingRepo.getReferenceById(id);
    }
}
