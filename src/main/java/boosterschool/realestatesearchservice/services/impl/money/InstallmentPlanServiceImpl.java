package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.enums.CheckBox;
import boosterschool.realestatesearchservice.models.money.InstallmentPlan;
import boosterschool.realestatesearchservice.repositories.money.InstallmentPlanRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.money.InstallmentPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstallmentPlanServiceImpl implements  InstallmentPlanService {
    private final InstallmentPlanRepo installmentPlanRepo;
    @Override
    public List<CheckBox> getDropDownList() {
        List<InstallmentPlan> installmentPlanList=installmentPlanRepo.findAll();
        return installmentPlanList.stream()
                .filter(InstallmentPlan::isActive)
                .map(InstallmentPlan::getMark)
                .collect(Collectors.toList());
    }

    @Override
    public InstallmentPlan getInstallmentPlanById(Integer id) {
        return installmentPlanRepo.getReferenceById(id);
    }
}
