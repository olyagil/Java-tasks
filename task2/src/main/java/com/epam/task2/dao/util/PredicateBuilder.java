package com.epam.task2.dao.util;

import com.epam.task2.entity.Country;
import com.epam.task2.entity.Hotel;
import com.epam.task2.entity.Tour;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder {

    private static final String DATE = "date";
    private static final String DURATION = "duration";
    private static final String TOUR_TYPE = "tourType";
    private static final String COST = "cost";
    private static final String NAME = "name";
    private static final String COUNTRY = "country";
    private static final String STARS = "stars";
    private static final String HOTEL = "hotel";

    private PredicateBuilder() {
    }

    public static List<Predicate> buildPredicate(List<SearchParameter> parameters,
                                                 EntityManager entityManager,
                                                 Root<Tour> tourRoot) {
        int count = 0;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        for (SearchParameter parameter : parameters) {
            switch (parameter) {
                case COUNTRY:
                    predicates.add(buildCountryPredicate(
                            (String) parameters.get(count).getValue(),
                            entityManager, tourRoot));
                    break;

                case TOUR_DATE:
                    predicates.add(criteriaBuilder.equal(tourRoot.get(
                            DATE), parameters.get(count).getValue()));
                    break;

                case TOUR_DURATION:
                    predicates.add(criteriaBuilder.equal(tourRoot.get(
                            DURATION), parameters.get(count).getValue()));
                    break;
                case TOUR_TYPE:
                    predicates.add(criteriaBuilder.equal(tourRoot.get(
                            TOUR_TYPE), parameters.get(count).getValue()));
                    break;

                case TOUR_MIN_COST:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo
                            (tourRoot.get(COST),
                                    (BigDecimal) parameters.get(count).getValue()));
                    break;

                case TOUR_MAX_COST:
                    predicates.add(criteriaBuilder
                            .greaterThanOrEqualTo(tourRoot.get(COST),
                                    (BigDecimal) parameters.get(count).getValue()));
                    break;

                case HOTEL_STARS:
                    predicates.add(buildHotelPredicate(
                            (Integer) parameters.get(count).getValue(),
                            entityManager, tourRoot));
                    break;


            }
            count++;
        }
        return predicates;
    }

    private static Predicate buildCountryPredicate(String countryName,
                                                   EntityManager entityManager,
                                                   Root tourRoot) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> query = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = query.from(Country.class);
        query.select(countryRoot).where(criteriaBuilder.equal(countryRoot.get(NAME),
                countryName));
        Country country = entityManager.createQuery(query).getSingleResult();
        return criteriaBuilder.equal(tourRoot.get(COUNTRY), country.getId());
    }

    private static Predicate buildHotelPredicate(Integer hotelName,
                                                 EntityManager entityManager,
                                                 Root tourRoot) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> query = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = query.from(Hotel.class);
        query.select(hotelRoot).where(criteriaBuilder.equal(hotelRoot.get(
                STARS), hotelName));
        Hotel hotel = entityManager.createQuery(query).getSingleResult();
        return criteriaBuilder.equal(tourRoot.get(HOTEL), hotel.getId());

    }
}
