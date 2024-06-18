package boosterschool.realestatesearchservice.controllers;

import boosterschool.realestatesearchservice.enums.CheckBox;
import boosterschool.realestatesearchservice.models.money.Mortgage;
import boosterschool.realestatesearchservice.models.object.*;
import boosterschool.realestatesearchservice.services.impl.object.*;
import boosterschool.realestatesearchservice.services.location.LocationService;
import boosterschool.realestatesearchservice.services.money.*;
import boosterschool.realestatesearchservice.services.object.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/real-estate/dropDownLists")
public class DropDownListsController {
    private final BuildingTypeService buildingTypeService;
    private final ConditionService conditionService;
    private final DealTypeService dealTypeService;
    private final HeatingService heatingService;
    private final HousingComplexService housingComplexService;
    private final PropertyTypeService propertyTypeService;
    private final RoomCountService roomCountService;
    private final SeriesService seriesService;
    private final LocationService locationService;
    private final CurrencyService currencyService;
    private final MortgageService mortgageService;
    private final InstallmentPlanService installmentPlanService;
    private final ExchangeOptionService exchangeOptionService;
    private final PriceTypeService priceTypeService;

    @GetMapping("/get-builder-type")
    public ResponseEntity<List<String>> getBuildingTypeList() {
        return ResponseEntity.ok(buildingTypeService.getDropDownList());
    }

    @GetMapping("/get-condition")
    public ResponseEntity<List<String>> getConditionList() {
        return ResponseEntity.ok(conditionService.getDropDownList());
    }

    @GetMapping("/get-deal-type")
    public ResponseEntity<List<String>> getDealTypeList() {
        return ResponseEntity.ok(dealTypeService.getDropDownList());
    }

    @GetMapping("/get-heating")
    public ResponseEntity<List<String>> getHeatingList() {
        return ResponseEntity.ok(heatingService.getDropDownList());
    }

    @GetMapping("/get-housing-complex")
    public ResponseEntity<List<String>> getHousingComplexList() {
        return ResponseEntity.ok(housingComplexService.getDropDownList());
    }

    @GetMapping("/get-property-type")
    public ResponseEntity<List<String>> getPropertyTypeList() {
        return ResponseEntity.ok(propertyTypeService.getDropDownList());
    }

    @GetMapping("/get-room-count")
    public ResponseEntity<List<Integer>> getRoomCountList() {
        return ResponseEntity.ok(roomCountService.getDropDownList());
    }

    @GetMapping("/get-series")
    public ResponseEntity<List<String>> getSeriesList() {
        return ResponseEntity.ok(seriesService.getDropDownList());
    }

    @GetMapping("/get-location")
    public ResponseEntity<List<String>> getLocationList() {
        return ResponseEntity.ok(locationService.getDropDownList());
    }

    @GetMapping("/get-currency")
    public ResponseEntity<List<String>> getCurrencyList() {
        return ResponseEntity.ok(currencyService.getDropDownList());
    }

    @GetMapping("/get-mortgage")
    public ResponseEntity<List<CheckBox>> getMortgageList() {
        return ResponseEntity.ok(mortgageService.getDropDownList());
    }

    @GetMapping("/get-installment-plan")
    public ResponseEntity<List<CheckBox>> getInstallmentPlanList() {
        return ResponseEntity.ok(installmentPlanService.getDropDownList());
    }

    @GetMapping("/get-exchange-option")
    public ResponseEntity<List<String>> getExchangeOptionList() {
        return ResponseEntity.ok(exchangeOptionService.getDropDownList());
    }

    @GetMapping("/get-price-type")
    public ResponseEntity<List<String>> getPriceTypeList() {
        return ResponseEntity.ok(priceTypeService.getDropDownList());
    }

}
