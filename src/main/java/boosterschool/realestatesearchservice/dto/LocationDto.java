package boosterschool.realestatesearchservice.dto;

import boosterschool.realestatesearchservice.models.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record LocationDto(Location region, Location city, Location district) {
}
