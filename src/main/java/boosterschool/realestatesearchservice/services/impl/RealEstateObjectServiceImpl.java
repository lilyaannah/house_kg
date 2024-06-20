package boosterschool.realestatesearchservice.services.impl;

import boosterschool.realestatesearchservice.dto.request.HouseKgObjectDto;
import boosterschool.realestatesearchservice.dto.request.RealEstateGetAdDtoResponse;
import boosterschool.realestatesearchservice.exceptions.ListNullExp;
import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.money.*;
import boosterschool.realestatesearchservice.models.object.*;
import boosterschool.realestatesearchservice.repositories.object.*;
import boosterschool.realestatesearchservice.services.impl.money.CurrentExchangeServiceImpl;
import boosterschool.realestatesearchservice.services.location.LocationService;
import boosterschool.realestatesearchservice.services.money.*;
import boosterschool.realestatesearchservice.services.object.*;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static boosterschool.realestatesearchservice.enums.ExceptionCode.LIST_IS_NULL;

@Service
@Builder
@AllArgsConstructor
public class RealEstateObjectServiceImpl implements RealEstateObjectService {
    private final RealEstateObjectRepo realEstateObjectRepo;
    private final SeriesService seriesService;
    private final RoomCountService roomCountService;
    private final PropertyTypeService propertyTypeService;
    private final HousingComplexService housingComplexService;
    private final HeatingService heatingService;
    private final DealTypeService dealTypeService;
    private final BuildingTypeService buildingTypeService;
    private final ConditionService conditionService;
    private final LocationService locationService;
    private final CurrencyService currencyService;
    private final PriceTypeService priceTypeService;
    private final InstallmentPlanService installmentPlanService;
    private final MortgageService mortgageService;
    private final ExchangeOptionService exchangeOptionService;
    private final PriceService priceService;
    private final CurrentExchangeServiceImpl currentExchangeRateService;

    @Override
    public List<RealEstateObject> getObject(int page, int size,
            String dealType,
                                                      String propertyType,
                                                      Integer roomCount,
                                                      String housingComplex,
                                                      String series,
                                                      String buildingType,
                                                      LocalDate yearBuilt,
                                                      String heating,
                                                      String condition,
                                                      String region,
                                                      String city,
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
    ) {
        PageRequest pageRequest= PageRequest.of(page-1, size);
        Specification<RealEstateObject> parameters = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = buildPredicates(
                    dealType, propertyType, roomCount,
                    housingComplex, series, buildingType, yearBuilt,
                    heating, condition, region, city,
                    district, streetName, houseNumber, priceMin,
                    priceMax, currency, priceType, installmentPlan,
                    mortgage, exchangeOption,
                    root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<RealEstateObject> pages = realEstateObjectRepo.findAll(parameters, pageRequest);

        return pages.getContent();

    }

    public List<RealEstateGetAdDtoResponse> toDto(List<RealEstateObject> realEstateObjectList){
        return realEstateObjectList.stream()
                .filter(RealEstateObject::isActive)
                .map(
                        realEstateObject -> {
                            return new RealEstateGetAdDtoResponse(
                                    realEstateObject.getDealType().getTypeName(),
                                    realEstateObject.getPropertyType().getTypeName(),
                                    realEstateObject.getRoomCount() != null ? realEstateObject.getRoomCount().getRoomCount() : 0,
                                    realEstateObject.getHousingComplex() != null ?
                                            realEstateObject.getHousingComplex().getHousingComplexName() : "",
                                    realEstateObject.getSeries() != null ? realEstateObject.getSeries().getSeries() : null,
                                    realEstateObject.getBuildingType() != null ? realEstateObject.getBuildingType().getBuildingType() : "",
                                    realEstateObject.getYearBuilt() != null ? realEstateObject.getYearBuilt() : null,
                                    realEstateObject.getHeating() != null ? realEstateObject.getHeating().getHeatingType() : "",
                                    realEstateObject.getCondition() != null ? realEstateObject.getCondition().getConditionType() : "",
                                    realEstateObjectRepo.getRegion(realEstateObject.getLocation().getLocationName()),
                                    realEstateObjectRepo.getCity(realEstateObject.getLocation().getLocationName()),
                                    realEstateObject.getLocation().getLocationName(),
                                    realEstateObject.getStreetName(),
                                    realEstateObject.getHouseNumber() != null ? realEstateObject.getHouseNumber() : "",
                                    realEstateObject.getPrice().getTotalPrice(),
                                    realEstateObject.getPrice().getCurrency().getCurrency(),
                                    realEstateObject.getPrice().getPriceType().getPriceType(),
                                    realEstateObject.getInstallmentPlan().getMark(),
                                    realEstateObject.getMortgage().getMark(),
                                    realEstateObject.getExchangeOption().getOption()
                            );
                        }).collect(Collectors.toList());
    }

    private List<Predicate> buildPredicates(String dealType,
                                            String propertyType,
                                            Integer roomCount,
                                            String housingComplex,
                                            String series,
                                            String buildingType,
                                            LocalDate yearBuilt,
                                            String heating,
                                            String condition,
                                            String region,
                                            String city,
                                            String district,
                                            String streetName,
                                            String houseNumber,
                                            Double priceMin,
                                            Double priceMax,
                                            String currency,
                                            String priceType,
                                            String installmentPlan,
                                            String mortgage,
                                            String exchangeOption,
                                            Root<RealEstateObject> root,
                                            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (dealType != null) {
            Join<RealEstateObject, DealType> join = root.join("dealType");
            predicates.add(criteriaBuilder.like(join.get("typeName"), "%" + dealType + "%"));
        }
        if (propertyType != null) {
            Join<RealEstateObject, PropertyType> join = root.join("propertyType");
            predicates.add(criteriaBuilder.equal(join.get("typeName"), propertyType));
        }
        if (roomCount != null) {
            Join<RealEstateObject, RoomCount> join = root.join("roomCount");
            predicates.add(criteriaBuilder.equal(join.get("roomCount"), roomCount));
        }
        if (housingComplex != null) {
            Join<RealEstateObject, HousingComplex> join = root.join("housingComplex");
            predicates.add(criteriaBuilder.equal(join.get("housingComplexName"), housingComplex));
        }
        if (series != null) {
            Join<RealEstateObject, Series> join = root.join("series");
            predicates.add(criteriaBuilder.equal(join.get("series"), series));
        }
        if (buildingType != null) {
            Join<RealEstateObject, BuildingType> join = root.join("buildingType");
            predicates.add(criteriaBuilder.equal(join.get("buildingType"), buildingType));
        }
        if (yearBuilt != null) {
            predicates.add(criteriaBuilder.equal(root.get("yearBuilt"), yearBuilt));
        }
        if (heating != null) {
            Join<RealEstateObject, Heating> join = root.join("heating");
            predicates.add(criteriaBuilder.equal(join.get("heatingType"), heating));
        }
        if (condition != null) {
            Join<RealEstateObject, Condition> join = root.join("condition");
            predicates.add(criteriaBuilder.equal(join.get("conditionType"), condition));
        }

        if (region != null ) {
            Join<RealEstateObject, Location> join1 = root.join("location");
            Join<Location, Location> join2 = join1.join("location");
            Join<Location, Location> join3 = join2.join("location");
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(join3.get("locationName")), region.toUpperCase()));
        }

        if (city != null) {
            Join<RealEstateObject, Location> join1 = root.join("location");
            Join<Location, Location> join2 = join1.join("location");
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(join2.get("locationName")), city.toUpperCase()));
        }
        if (district != null) {
            Join<RealEstateObject, Location> join1 = root.join("location");
            predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(join1.get("locationName")), district.toUpperCase()));
        }

        if (streetName != null) {
            predicates.add(criteriaBuilder.equal(root.get("streetName"), streetName));
        }
        if (houseNumber != null) {
            predicates.add(criteriaBuilder.equal(root.get("houseNumber"), houseNumber));
        }

        if (currency != null) {
            CurrentExchangeRate currentExchangeRate = currentExchangeRateService.getCurrentExchangeRate();
            Expression<Double> convertedPriceExpression = null;

            if ("USD".equals(currency)) {
                convertedPriceExpression = root.get("price").get("totalPrice");
            } else if ("KGZ".equals(currency) && currentExchangeRate != null) {
                Expression<Double> exchangeRate = criteriaBuilder.literal(currentExchangeRate.getExchangeRate());
                convertedPriceExpression = criteriaBuilder.prod(root.get("price").get("totalPrice"), exchangeRate);
            }

            if (convertedPriceExpression != null) {
                if (priceMin != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(convertedPriceExpression, priceMin));
                }
                if (priceMax != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(convertedPriceExpression, priceMax));
                }
            }
        }

        if (priceType != null) {
            Join<RealEstateObject, Price> joinPrice = root.join("price");
            Join<Price, PriceType> join = joinPrice.join("priceType");
            predicates.add(criteriaBuilder.like(join.get("priceType"), "%" + priceType + "%"));
        }
        if (installmentPlan != null) {
            Join<RealEstateObject, InstallmentPlan> join = root.join("installmentPlan");
            predicates.add(criteriaBuilder.equal(join.get("mark"), installmentPlan));
        }
        if (mortgage != null) {
            Join<RealEstateObject, Mortgage> join = root.join("mortgage");
            predicates.add(criteriaBuilder.equal(join.get("mark"), mortgage));
        }
        if (exchangeOption != null) {
            Join<RealEstateObject, ExchangeOption> join = root.join("exchangeOption");
            predicates.add(criteriaBuilder.equal(join.get("option"), exchangeOption));
        }
        return predicates;
    }

    @Override
    public void postAd(HouseKgObjectDto houseKgObjectDto) {
        RealEstateObject realEstateObject = new RealEstateObject();
        DealType dealType = dealTypeService.getDealTypeById(houseKgObjectDto.dealType());
        PropertyType propertyType = propertyTypeService.getPropertyTypeById(houseKgObjectDto.propertyType());

        if (houseKgObjectDto.roomCount() != null) {
            RoomCount roomCount = roomCountService.getRoomCountById(houseKgObjectDto.roomCount());
            realEstateObject.setRoomCount(roomCount);
        }

        if (houseKgObjectDto.housingComplex() != null) {
            HousingComplex housingComplex = housingComplexService.getHousingComplexById(houseKgObjectDto.housingComplex());
            realEstateObject.setHousingComplex(housingComplex);
        }

        if (houseKgObjectDto.series() != null) {
            Series series = seriesService.getSeriesById(houseKgObjectDto.series());
            realEstateObject.setSeries(series);
        }

        if (houseKgObjectDto.buildingType() != null) {
            BuildingType buildingType = buildingTypeService.getBuildingTypeById(houseKgObjectDto.buildingType());
            realEstateObject.setBuildingType(buildingType);
        }

        if (houseKgObjectDto.heating() != null) {
            Heating heating = heatingService.getHeatingById(houseKgObjectDto.heating());
            realEstateObject.setHeating(heating);
        }

        if (houseKgObjectDto.condition() != null) {
            Condition condition = conditionService.getConditionById(houseKgObjectDto.condition());
            realEstateObject.setCondition(condition);
        }
        Location location = locationService.getLocationById(houseKgObjectDto.location());
        Currency currency = currencyService.getCurrencyById(houseKgObjectDto.currency());
        PriceType priceType = priceTypeService.getPriceTypeById(houseKgObjectDto.priceType());
        InstallmentPlan installmentPlan = installmentPlanService.getInstallmentPlanById(houseKgObjectDto.installmentPlan());
        Mortgage mortgage = mortgageService.getMortgageById(houseKgObjectDto.mortgage());
        ExchangeOption exchangeOption = exchangeOptionService.getExchangeOptionById(houseKgObjectDto.exchangeOption());

        Price price = new Price().setTotalPrice(houseKgObjectDto.price())
                .setActive(true).setCurrency(currency).setPriceType(priceType);
        price = priceService.save(price);

        realEstateObject.setDealType(dealType);
        realEstateObject.setPropertyType(propertyType);
        realEstateObject.setLocation(location);
        realEstateObject.setPrice(price);
        if (houseKgObjectDto.floor() != null) {
            realEstateObject.setFloor(houseKgObjectDto.floor());
        }
        realEstateObject.setInstallmentPlan(installmentPlan);
        realEstateObject.setMortgage(mortgage);
        realEstateObject.setExchangeOption(exchangeOption);
        if (houseKgObjectDto.yearBuilt() != null) {
            realEstateObject.setYearBuilt(houseKgObjectDto.yearBuilt());
        }
        realEstateObject.setStreetName(houseKgObjectDto.streetName());
        if (houseKgObjectDto.houseNumber() != null) {
            realEstateObject.setHouseNumber(houseKgObjectDto.houseNumber());
        }
        realEstateObject.setActive(true);

        realEstateObjectRepo.save(realEstateObject);
    }
}






