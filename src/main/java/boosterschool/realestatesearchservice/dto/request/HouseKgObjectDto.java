package boosterschool.realestatesearchservice.dto.request;

import boosterschool.realestatesearchservice.models.object.DealType;
import boosterschool.realestatesearchservice.models.object.PropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public record HouseKgObjectDto(@NotNull(message = "dealType must be not null")
                               @Min(value = 1, message = "dealType 1 (Продажа) / 2 (Снять)")
                               @Max(value = 2, message = "dealType 1 (Продажа) / 2 (Снять)")
                               Integer dealType,

                               @Min(value = 1, message = "propertyType must be greater than or equal to 1")
                               @Max(value = 7, message = "propertyType must be less than or equal to 7")
                               Integer propertyType,

                               @Min(value = 1, message = "roomCount must be greater than or equal to 1")
                               @Max(value = 7, message = "roomCount must be less than or equal to 7")
                               Integer roomCount,

                               @Min(value = 1, message = "housingComplex must be greater than or equal to 1")
                               @Max(value = 7, message = "housingComplex must be less than or equal to 7")
                               Integer housingComplex,
                               @JsonProperty("floor") Integer floor,

                               @Min(value = 1, message = "series must be greater than or equal to 1")
                               @Max(value = 8, message = "series must be less than or equal to 8")
                               Integer series,

                               @Min(value = 1, message = "series must be greater than or equal to 1")
                               @Max(value = 8, message = "series must be less than or equal to 8")
                               Integer buildingType,

                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                               LocalDate yearBuilt,

                               @Min(value = 1, message = "heating must be greater than or equal to 1")
                               @Max(value = 3, message = "heating must be less than or equal to 3")
                               Integer heating,

                               @Min(value = 1, message = "condition must be greater than or equal to 1")
                               @Max(value = 3, message = "condition must be less than or equal to 3")
                               Integer condition,

                               @Min(value = 4, message = "location must be greater than or equal to 4")
                               @Max(value = 17, message = "location must be less than or equal to 17")
                               Integer location,
                               String streetName,
                               String houseNumber,

                               @NotNull(message = "price must not be null")
                               double price,

                               @NotNull(message = "currency must not be null")
                               @Min(value = 1, message = "currency 1 (USD) / 2 (KGZ)")
                               @Max(value = 2, message = "currency 1 (USD) / 2 (KGZ)")
                               Integer currency,

                               @NotNull(message = "priceType must not be null")
                               @Min(value = 1, message = "priceType 1 (За всё) / 2 (За квадратный метр)")
                               @Max(value = 2, message = "priceType 1 (За всё) / 2 (За квадратный метр)")
                               Integer priceType,

                               @NotNull(message = "installmentPlan must not be null")
                               @Min(value = 1, message = "installmentPlan 1 (YES) / 2 (NO)")
                               @Max(value = 2, message = "installmentPlan 1 (YES) / 2 (NO)")
                               Integer installmentPlan,

                               @NotNull(message = "mortgage must not be null")
                               @Min(value = 1, message = "mortgage 1 (YES) / 2 (NO)")
                               @Max(value = 2, message = "mortgage 1 (YES) / 2 (NO)")
                               Integer mortgage,


                               @Min(value = 1, message = "exchangeOption 1 (Рассмотрю варианты) / " +
                                       "2 (С доплатой покупателя) /" +
                                       "3 (Ключ на ключ) /" +
                                       "4(С доплатой продавца) ")
                               @Max(value = 4, message = "exchangeOption 1 (Рассмотрю варианты) / " +
                                       "2 (С доплатой покупателя) /" +
                                       "3 (Ключ на ключ) /" +
                                       "4(С доплатой продавца) ")
                               Integer exchangeOption) {
}