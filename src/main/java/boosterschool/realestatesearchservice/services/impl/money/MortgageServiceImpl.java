package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.enums.CheckBox;
import boosterschool.realestatesearchservice.models.money.Mortgage;
import boosterschool.realestatesearchservice.repositories.money.MortgageRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.money.MortgageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MortgageServiceImpl implements  MortgageService {
    private final MortgageRepo mortgageRepo;
    @Override
    public List<CheckBox> getDropDownList() {
        List<Mortgage> mortgageList=mortgageRepo.findAll();
        return mortgageList.stream()
                .filter(Mortgage::isActive)
                .map(Mortgage::getMark)
                .collect(Collectors.toList());
    }

    @Override
    public Mortgage getMortgageById(Integer id) {
        return mortgageRepo.getReferenceById(id);
    }
}
