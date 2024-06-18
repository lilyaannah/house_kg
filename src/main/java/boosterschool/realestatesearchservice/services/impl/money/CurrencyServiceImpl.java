package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.models.money.Currency;
import boosterschool.realestatesearchservice.repositories.money.CurrencyRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.money.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepo currencyRepo;
    @Override
    public List<String> getDropDownList() {
        List<Currency> currencyList=currencyRepo.findAll();
        return currencyList.stream()
                .filter(Currency::isActive)
                .map(Currency::getCurrency)
                .collect(Collectors.toList());
    }

    @Override
    public Currency getCurrencyById(Integer id) {
        return currencyRepo.getReferenceById(id);
    }
}
