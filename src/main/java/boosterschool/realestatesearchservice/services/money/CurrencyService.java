package boosterschool.realestatesearchservice.services.money;

import boosterschool.realestatesearchservice.models.money.Currency;

import java.util.List;

public interface CurrencyService {
    Currency getCurrencyById(Integer id);
    List<String> getDropDownList();
}
