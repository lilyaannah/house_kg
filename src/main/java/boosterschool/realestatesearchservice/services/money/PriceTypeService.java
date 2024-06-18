package boosterschool.realestatesearchservice.services.money;

import boosterschool.realestatesearchservice.models.money.PriceType;

import java.util.List;

public interface PriceTypeService {
    PriceType getPriceTypeById(Integer id);
    List<String> getDropDownList();
}
