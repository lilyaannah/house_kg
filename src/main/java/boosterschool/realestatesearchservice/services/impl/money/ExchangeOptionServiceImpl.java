package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.models.money.ExchangeOption;
import boosterschool.realestatesearchservice.repositories.money.ExchangeOptionRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.money.ExchangeOptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExchangeOptionServiceImpl implements ExchangeOptionService {
    private final ExchangeOptionRepo exchangeOptionRepo;
    @Override
    public List<String> getDropDownList() {
        List<ExchangeOption> exchangeOptionList=exchangeOptionRepo.findAll();
        return exchangeOptionList.stream()
                .filter(ExchangeOption::isActive)
                .map(ExchangeOption::getOption)
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeOption getExchangeOptionById(Integer id) {
        return exchangeOptionRepo.getReferenceById(id);
    }
}
