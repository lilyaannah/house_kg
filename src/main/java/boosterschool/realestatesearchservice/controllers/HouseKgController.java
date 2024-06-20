package boosterschool.realestatesearchservice.controllers;

import boosterschool.realestatesearchservice.dto.request.HouseKgObjectDto;
import boosterschool.realestatesearchservice.dto.request.RealEstateGetAdDtoResponse;
import boosterschool.realestatesearchservice.exceptions.ListNullExp;
import boosterschool.realestatesearchservice.exceptions.validator.CustomValidationNotBlank;
import boosterschool.realestatesearchservice.models.money.CurrentExchangeRate;
import boosterschool.realestatesearchservice.models.object.RealEstateObject;
import boosterschool.realestatesearchservice.services.impl.RealEstateObjectServiceImpl;
import boosterschool.realestatesearchservice.services.impl.money.CurrentExchangeServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static boosterschool.realestatesearchservice.enums.ExceptionCode.LIST_IS_NULL;

@Validated
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
    public ResponseEntity<String> postMethod(@Valid @RequestBody HouseKgObjectDto houseKgObjectDto
    ) {
        realEstateObjectService.postAd(houseKgObjectDto);
        return new ResponseEntity<>("Ad created", HttpStatus.OK);
    }

    @GetMapping("/get-ads")
    public ResponseEntity<List<RealEstateGetAdDtoResponse>> getMethod(
            @RequestParam(required = false, defaultValue = "1")
            int page,
            @RequestParam(required = false, defaultValue = "20")
            int size,

            @RequestParam
            @NotBlank(message = "DealType cant be blank")
            String dealType,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "PropertyType cannot be empty or contain only spaces")
            String propertyType,

            @RequestParam(required = false)
            @Positive(message = "Room count must be a positive number")
            @Min(value = 1, message = "Room count must be at least 1")
            Integer roomCount,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "HousingComplex cannot be empty or contain only spaces")
            String housingComplex,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "Series cannot be empty or contain only spaces")
            String series,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "BuildingType cannot be empty or contain only spaces")
            String buildingType,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate yearBuilt,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "Heating cannot be empty or contain only spaces")
            String heating,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "Condition cannot be empty or contain only spaces")
            String condition,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "Region cannot be empty or contain only spaces")
            String region,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "City cannot be empty or contain only spaces")
            String city,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "District cannot be empty or contain only spaces")
            String district,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "StreetName cannot be empty or contain only spaces")
            String streetName,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "HouseNumber cannot be empty or contain only spaces")
            String houseNumber,

            @RequestParam(required = false)
            @PositiveOrZero
            Double priceMin,

            @RequestParam(required = false)
            @PositiveOrZero
            Double priceMax,

            @RequestParam
            @NotBlank(message = "Currency cant be blank")
            @Pattern(regexp = "^(?:USD|KGZ)$", message = "Currency must be 'USD' or 'KGZ'")
            String currency,

            @RequestParam
            @NotBlank(message = "PriceType cant be blank")
            String priceType,

            @RequestParam(required = false)
            @Pattern(regexp = "^(?:NO|YES)$", message = "InstallmentPlan must be 'NO' or 'YES'")
            @CustomValidationNotBlank(message = "InstallmentPlan cannot be empty or contain only spaces")
            String installmentPlan,

            @RequestParam(required = false)
            @Pattern(regexp = "^(?:NO|YES)$", message = "Mortgage must be 'NO' or 'YES'")
            @CustomValidationNotBlank(message = "Mortgage cannot be empty or contain only spaces")
            String mortgage,

            @RequestParam(required = false)
            @CustomValidationNotBlank(message = "ExchangeOption cannot be empty or contain only spaces")
            String exchangeOption) {

        if ((region == null && city != null) || (city == null && district != null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }

        List<RealEstateObject> realEstateObjectList = realEstateObjectService.getObject(
                page,
                size,
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
                exchangeOption);

        List<RealEstateGetAdDtoResponse> realEstateObject =
                realEstateObjectService.toDto(realEstateObjectList);

        if(realEstateObject.isEmpty()){
            throw new ListNullExp(LIST_IS_NULL.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(realEstateObject);
    }

}
