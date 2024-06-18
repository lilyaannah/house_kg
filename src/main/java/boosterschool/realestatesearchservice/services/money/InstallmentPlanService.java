package boosterschool.realestatesearchservice.services.money;

import boosterschool.realestatesearchservice.enums.CheckBox;
import boosterschool.realestatesearchservice.models.money.InstallmentPlan;

import java.util.List;

public interface InstallmentPlanService {
    InstallmentPlan getInstallmentPlanById(Integer id);
    List<CheckBox> getDropDownList();
}
