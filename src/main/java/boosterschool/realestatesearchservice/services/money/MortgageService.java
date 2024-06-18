package boosterschool.realestatesearchservice.services.money;

import boosterschool.realestatesearchservice.enums.CheckBox;
import boosterschool.realestatesearchservice.models.money.Mortgage;

import java.util.List;

public interface MortgageService {
    Mortgage getMortgageById(Integer id);
    List<CheckBox> getDropDownList();
}
