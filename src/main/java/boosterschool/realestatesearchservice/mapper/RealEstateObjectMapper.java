package boosterschool.realestatesearchservice.mapper;

import boosterschool.realestatesearchservice.dto.response.RealEstateGetAdDtoResponse;
import boosterschool.realestatesearchservice.models.object.RealEstateObject;

public interface RealEstateObjectMapper {


    RealEstateGetAdDtoResponse realEstateObjectToDto(RealEstateObject realEstateObject);
}
