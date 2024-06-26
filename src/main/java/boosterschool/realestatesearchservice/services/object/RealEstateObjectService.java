package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.dto.request.HouseKgObjectDto;
import boosterschool.realestatesearchservice.dto.request.RealEstateGetAdDtoResponse;
import boosterschool.realestatesearchservice.models.object.RealEstateObject;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface RealEstateObjectService {
    void postAd(HouseKgObjectDto houseKgObjectDto);

    List<RealEstateObject> getObject(int page,
                                     int size,
                                     String dealType,
                                     String propertyTypeName,
                                     Integer roomCount,
                                     String housingComplex,
                                     String series,
                                     String buildingType,
                                     LocalDate yearBuilt,
                                     String heating,
                                     String condition,
                                     String region,
                                     String settlement,
                                     String district,
                                     String streetName,
                                     String houseNumber,
                                     Double priceMin,
                                     Double priceMax,
                                     String currency,
                                     String priceType,
                                     String installmentPlan,
                                     String mortgage,
                                     String exchangeOption
    );
}
