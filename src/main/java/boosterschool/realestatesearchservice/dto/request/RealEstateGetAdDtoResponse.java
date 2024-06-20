package boosterschool.realestatesearchservice.dto.request;

import boosterschool.realestatesearchservice.enums.CheckBox;
import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.money.*;
import boosterschool.realestatesearchservice.models.object.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public record RealEstateGetAdDtoResponse(@JsonProperty("deal_type") String dealType,
                                         @JsonProperty("property_type") String propertyType,
                                         @JsonProperty("room_count") int roomCount,
                                         @JsonProperty("housing_complex") String housingComplex,
                                         @JsonProperty("series") String series,
                                         @JsonProperty("building_type") String buildingType,
                                         @JsonProperty("year_built") LocalDate yearBuilt,
                                         @JsonProperty("heating") String heating,
                                         @JsonProperty("condition") String condition,
                                         @JsonProperty("region") String region,
                                         @JsonProperty("settlement") String city,
                                         @JsonProperty("district") String district,
                                         @JsonProperty("street_name") String streetName,
                                         @JsonProperty("house_number") String houseNumber,
                                         @JsonProperty("price") double price,
                                         @JsonProperty("currency") String currency,
                                         @JsonProperty("price_type") String priceType,
                                         @JsonProperty("installment_plan") CheckBox installmentPlan,
                                         @JsonProperty("mortgage") CheckBox mortgage,
                                         @JsonProperty("exchange_option") String exchangeOption) {
}

