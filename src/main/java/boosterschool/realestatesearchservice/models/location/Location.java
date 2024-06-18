package boosterschool.realestatesearchservice.models.location;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "location_name")
    String locationName;

    @Column(name = "active")
    boolean active;

    @ManyToOne
    @JoinColumn(name = "location_type_id")
    LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

}
