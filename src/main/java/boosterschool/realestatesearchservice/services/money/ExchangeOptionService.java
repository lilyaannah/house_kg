package boosterschool.realestatesearchservice.services.money;

import boosterschool.realestatesearchservice.models.money.ExchangeOption;

import java.util.List;

public interface ExchangeOptionService {
    ExchangeOption getExchangeOptionById(Integer id);
    List<String> getDropDownList();
}
