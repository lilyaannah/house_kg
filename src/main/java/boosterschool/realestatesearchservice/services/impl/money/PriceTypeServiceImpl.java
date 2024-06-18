package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.models.money.PriceType;
import boosterschool.realestatesearchservice.repositories.money.PriceTypeRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.money.PriceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PriceTypeServiceImpl implements  PriceTypeService {
    private final PriceTypeRepo priceTypeRepo;
    @Override
    public List<String> getDropDownList() {
        List<PriceType>priceTypeList=priceTypeRepo.findAll();
        return priceTypeList.stream()
                .filter(PriceType::isActive)
                .map(PriceType::getPriceType)
                .collect(Collectors.toList());
    }

    @Override
    public PriceType getPriceTypeById(Integer id) {
        return priceTypeRepo.getReferenceById(id);
    }
}
