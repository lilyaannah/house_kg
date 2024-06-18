package boosterschool.realestatesearchservice.dto;

import boosterschool.realestatesearchservice.models.object.DealType;
import boosterschool.realestatesearchservice.models.object.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public record HouseKgObjectDto(Integer dealTypeId,
                               Integer propertyTypeId,
                               Integer roomCount,
                               Integer housingComplex,
                               @JsonProperty("floor") Integer floor,
                               Integer series,
                               Integer buildingType,
                               LocalDate yearBuilt,
                               Integer heating,
                               Integer condition,
                               Integer location,
                               String streetName,
                               String houseNumber,
                               double price,
                               Integer currency,
                               Integer priceType,
                               Integer installmentPlan,
                               Integer mortgage,
                               Integer exchangeOption ) {
}
