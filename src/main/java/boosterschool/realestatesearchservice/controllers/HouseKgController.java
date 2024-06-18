package boosterschool.realestatesearchservice.controllers;

import boosterschool.realestatesearchservice.dto.HouseKgObjectDto;
import boosterschool.realestatesearchservice.dto.response.RealEstateGetAdDtoResponse;
import boosterschool.realestatesearchservice.models.money.CurrentExchangeRate;
import boosterschool.realestatesearchservice.models.object.*;
import boosterschool.realestatesearchservice.services.impl.RealEstateObjectServiceImpl;
import boosterschool.realestatesearchservice.services.impl.money.CurrentExchangeServiceImpl;
import boosterschool.realestatesearchservice.services.impl.object.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping("/api/v1/real-estate")
public class HouseKgController {
    final RealEstateObjectServiceImpl realEstateObjectService;
    final CurrentExchangeServiceImpl exchangeRateService;

    @GetMapping("/currentExchangeRate")
    public ResponseEntity<CurrentExchangeRate> getCurrentExchangeRate() {
        CurrentExchangeRate currentRate = exchangeRateService.getCurrentExchangeRate();
        if (currentRate != null) {
            return ResponseEntity.ok(currentRate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/updateExchangeRate")
    public ResponseEntity<CurrentExchangeRate> updateExchangeRate(@RequestParam double newRate) {
        CurrentExchangeRate updatedRate = exchangeRateService.updateExchangeRate(newRate);
        return ResponseEntity.ok(updatedRate);
    }

    @PostMapping("/post-ads")
    public ResponseEntity<String> postMethod(@RequestBody HouseKgObjectDto houseKgObjectDto
    ) {
        realEstateObjectService.postAd(houseKgObjectDto);
        return new ResponseEntity<>("Ad created", HttpStatus.OK);
    }

    @GetMapping("/get-ads")
    public ResponseEntity<List<RealEstateGetAdDtoResponse>> getMethod(@RequestParam(required = true) String dealType,
                                                                      @RequestParam(required = false) String propertyType,
                                                                      @RequestParam(required = false) Integer roomCount,
                                                                      @RequestParam(required = false) String housingComplex,
                                                                      @RequestParam(required = false) String series,
                                                                      @RequestParam(required = false) String buildingType,
                                                                      @RequestParam(required = false) LocalDate yearBuilt,
                                                                      @RequestParam(required = false) String heating,
                                                                      @RequestParam(required = false) String condition,
                                                                      @RequestParam(required = false) String region,
                                                                      @RequestParam(required = false) String city,
                                                                      @RequestParam(required = false) String district,
                                                                      @RequestParam(required = false) String streetName,
                                                                      @RequestParam(required = false) String houseNumber,
                                                                      @RequestParam(required = false) Double priceMin,
                                                                      @RequestParam(required = false) Double priceMax,
                                                                      @RequestParam(required = true) String currency,
                                                                      @RequestParam(required = true) String priceType,
                                                                      @RequestParam(required = false) String installmentPlan,
                                                                      @RequestParam(required = false) String mortgage,
                                                                      @RequestParam(required = false) String exchangeOption) {
        if ((region == null && city != null) || (city == null && district != null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }

        return ResponseEntity.ok(realEstateObjectService.getObject(
                dealType,
                propertyType,
                roomCount,
                housingComplex,
                series,
                buildingType,
                yearBuilt,
                heating,
                condition,
                region,
                city,
                district,
                streetName,
                houseNumber,
                priceMin,
                priceMax,
                currency,
                priceType,
                installmentPlan,
                mortgage,
                exchangeOption));
    }

}
