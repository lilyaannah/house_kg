package boosterschool.realestatesearchservice.services.impl;

import boosterschool.realestatesearchservice.dto.HouseKgObjectDto;
import boosterschool.realestatesearchservice.dto.response.RealEstateGetAdDtoResponse;
import boosterschool.realestatesearchservice.mapper.RealEstateObjectMapper;
import boosterschool.realestatesearchservice.models.location.Location;
import boosterschool.realestatesearchservice.models.money.*;
import boosterschool.realestatesearchservice.models.object.*;
import boosterschool.realestatesearchservice.repositories.object.*;
import boosterschool.realestatesearchservice.services.impl.money.CurrentExchangeServiceImpl;
import boosterschool.realestatesearchservice.services.location.LocationService;
import boosterschool.realestatesearchservice.services.money.*;
import boosterschool.realestatesearchservice.services.object.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<RealEstateGetAdDtoResponse> getObject(String dealType,
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
        Specification<RealEstateObject> parameters = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = buildPredicates(dealType,
                    propertyType, roomCount,
                    housingComplex, series,
                    buildingType, yearBuilt,
                    heating, condition,
                    region, city,
                    district, streetName,
                    houseNumber, priceMin,
                    priceMax, currency,
                    priceType, installmentPlan,
                    mortgage, exchangeOption,
                    root, criteriaBuilder);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        //realEstateObjectRepo.findAll(parameters);

       // realEstateObjectList.forEach(obj -> System.out.println(obj.toString()));

        return realEstateObjectRepo.findAll(parameters).stream()
                .filter(RealEstateObject::isActive)
                .map(
                realEstateObject -> {
                    String regionName = getLocationNameByType(realEstateObject.getLocation(), "region");
                    String cityName = getLocationNameByType(realEstateObject.getLocation(), "city");
                    String districtName = getLocationNameByType(realEstateObject.getLocation(), "district");
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
                            regionName,
                            cityName,
                            districtName,
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

    // Метод для получения locationName по типу location
    private String getLocationNameByType(Location location, String type) {
        while (location != null) {
            if (location.getLocationType().getLocationType().equalsIgnoreCase(type)) {
                return location.getLocationName();
            }
            location = location.getLocation();
        }
        return "";
    }

//    private String getRegion(Location location) {
//        while (location.getLocation().getLocationName() != null) {
//            location = location.getLocation();
//        }
//        return location.getLocationName();
//    }
//
//    private String getCity(Location location) {
//        Location parent = location.getLocation();
//        while (parent != null && parent.getLocationName()!= null) {
//            location = parent;
//            parent = location.getLocation();
//        }
//        return location.getLocationName();
//    }
//
//    private String getDistrict(Location location) {
//        return location.getLocationName();
//    }

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
        if (region != null) {
            if (!"Любой регион".equalsIgnoreCase(region)) {
                Join<RealEstateObject, Location> join = root.join("location");

                // Self-join to find parent locations
                Join<Location, Location> parentJoin = join.join("location", JoinType.LEFT);

                // Check for the specified region or its parent location
                Predicate regionPredicate = criteriaBuilder.or(
                        criteriaBuilder.equal(join.get("locationName"), region),
                        criteriaBuilder.equal(parentJoin.get("locationName"), region)
                );

                predicates.add(regionPredicate);
            }
        }

        if (city != null) {
            if (!"Любой город".equalsIgnoreCase(city)) {
                Join<RealEstateObject, Location> join = root.join("location");

                // Self-join to find parent locations
                Join<Location, Location> parentJoin = join.join("location", JoinType.LEFT);

                // Check for the specified city or its parent location
                Predicate cityPredicate = criteriaBuilder.or(
                        criteriaBuilder.equal(join.get("locationName"), city),
                        criteriaBuilder.equal(parentJoin.get("locationName"), city)
                );

                predicates.add(cityPredicate);
            }
        }

        if (district != null) {
            if (!"Любой район".equalsIgnoreCase(district)) {
                Join<RealEstateObject, Location> join = root.join("location");

                // Self-join to find parent locations
                Join<Location, Location> parentJoin = join.join("location", JoinType.LEFT);

                // Check for the specified district or its parent location
                Predicate districtPredicate = criteriaBuilder.or(
                        criteriaBuilder.equal(join.get("locationName"), district),
                        criteriaBuilder.equal(parentJoin.get("locationName"), district)
                );

                predicates.add(districtPredicate);
            }
        }

        if (streetName != null) {
            predicates.add(criteriaBuilder.equal(root.get("streetName"), streetName));
        }
        if (houseNumber != null) {
            predicates.add(criteriaBuilder.equal(root.get("houseNumber"), houseNumber));
        }
//        if (currency != null) {
//            Join<RealEstateObject, Price> joinPrice = root.join("price"); // Соединяем с таблицей Price
//            Join<Price, Currency> join = joinPrice.join("currency");
//            predicates.add(criteriaBuilder.like(join.get("currency"), "%" + currency + "%"));
//
//
//
//            if (priceMin != null) {
//                Join<RealEstateObject, Price> joinMin = root.join("price");
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(joinMin.get("totalPrice"), priceMin));
//            }
//            if (priceMax != null) {
//                Join<RealEstateObject, Price> joinMax = root.join("price");
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(joinMax.get("totalPrice"), priceMax));
//            }
//        }

//        if (currency != null) {
////            Join<RealEstateObject, Price> joinPrice = root.join("price"); // Соединяем с таблицей Price
////            Join<Price, Currency> join = joinPrice.join("currency");
////            predicates.add(criteriaBuilder.like(join.get("currency"), "%" + currency + "%"));
//
//            // Получаем текущий курс обмена
//            CurrentExchangeRate currentExchangeRate = currentExchangeRateService.getCurrentExchangeRate();
//            Expression<Double> convertedPriceExpression = null;
//
//            if ("Доллар".equals(currency)) {
//                // Если введена валюта USD, оставляем цены без изменений
//                convertedPriceExpression = root.get("price").get("totalPrice");
//            } else if (currentExchangeRate != null) {
//                // Конвертируем цены в доллары или сомы в зависимости от курса
//                if ("Сом".equals(currency)) {
//                    // Конвертируем сомы в доллары
//                    Expression<Double> exchangeRate = criteriaBuilder.literal(1.0 / currentExchangeRate.getExchangeRate());
//                    convertedPriceExpression = criteriaBuilder.prod(root.get("price").get("totalPrice"), exchangeRate);
//                }
//            }
//
//            if (convertedPriceExpression != null) {
//                // Добавляем условия для фильтрации по сконвертированным ценам
//                if (priceMin != null) {
//                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(convertedPriceExpression, priceMin));
//                }
//                if (priceMax != null) {
//                    predicates.add(criteriaBuilder.lessThanOrEqualTo(convertedPriceExpression, priceMax));
//                }
//            }
//        }

        if (currency != null) {
            // Получаем текущий курс обмена
            CurrentExchangeRate currentExchangeRate = currentExchangeRateService.getCurrentExchangeRate();
            Expression<Double> convertedPriceExpression = null;

            if ("Доллар".equals(currency)) {
                // Если введена валюта USD, оставляем цены без изменений
                convertedPriceExpression = root.get("price").get("totalPrice");
            } else if ("Сом".equals(currency) && currentExchangeRate != null) {
                // Конвертируем сомы в доллары
                Expression<Double> exchangeRate = criteriaBuilder.literal(currentExchangeRate.getExchangeRate());
                convertedPriceExpression = criteriaBuilder.prod(root.get("price").get("totalPrice"), exchangeRate);
            }

            if (convertedPriceExpression != null) {
                // Добавляем условия для фильтрации по сконвертированным ценам
                if ("Сом".equals(currency) && priceMin != null) {
                    // Если валюта Сом и задана минимальная цена, применяем фильтр
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(convertedPriceExpression, priceMin));
                } else if ("Сом".equals(currency) && priceMax != null) {
                    // Если валюта Сом и задана максимальная цена, применяем фильтр
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(convertedPriceExpression, priceMax));
                }
            }
        }

        if (priceType != null) {
            Join<RealEstateObject, Price> joinPrice = root.join("price"); // Соединяем с таблицей Price
            Join<Price, PriceType> join = joinPrice.join("priceType"); // Соединяем с таблицей PriceType внутри Price
            predicates.add(criteriaBuilder.like(join.get("priceType"), "%" + priceType + "%")); // Сравниваем атрибут typeName в PriceType
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
        DealType dealType = dealTypeService.getDealTypeById(houseKgObjectDto.dealTypeId());
        PropertyType propertyType = propertyTypeService.getPropertyTypeById(houseKgObjectDto.propertyTypeId());

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






