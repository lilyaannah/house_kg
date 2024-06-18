package boosterschool.realestatesearchservice.models.object;

import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.money.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
@Table(name = "real_estate_objects")
public class RealEstateObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="year_built")
    LocalDate yearBuilt;

    @Column(name = "floor")
    int floor;

    @JoinColumn(name = "street_name")
    String streetName;

    @JoinColumn(name="house_number")
    String houseNumber;

    @Column(name = "active")
    boolean active;

    @ManyToOne
    @JoinColumn(name = "deal_type_id")
    DealType dealType;

    @ManyToOne
    @JoinColumn(name = "building_type_id")
    BuildingType buildingType;

    @ManyToOne
    @JoinColumn(name = "condition_id")
    Condition condition;

    @ManyToOne
    @JoinColumn(name = "heating_id")
    Heating heating;

    @ManyToOne
    @JoinColumn(name = "housing_complex_id")
    HousingComplex housingComplex;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "room_count_id")
    RoomCount roomCount;

    @ManyToOne
    @JoinColumn(name = "series_id")
    Series series;

    @ManyToOne
    @JoinColumn(name="location_id")
    Location location;

    @ManyToOne
    @JoinColumn(name="price_id")
    Price price;

    @ManyToOne
    @JoinColumn(name="installment_plan_id")
    InstallmentPlan installmentPlan;

    @ManyToOne
    @JoinColumn(name="mortgage_id")
    Mortgage mortgage;

    @ManyToOne
    @JoinColumn(name="exchange_option_id")
    ExchangeOption exchangeOption;



    public RealEstateObject(Integer integer, Integer integer1) {
    }
}
