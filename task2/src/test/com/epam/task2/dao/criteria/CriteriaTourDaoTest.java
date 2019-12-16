package com.epam.task2.dao.criteria;

import com.epam.task2.config.AppConfig;
import com.epam.task2.dao.TourDao;
import com.epam.task2.entity.Country;
import com.epam.task2.entity.Hotel;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.enumerution.Feature;
import com.epam.task2.entity.enumerution.TourType;
import com.epam.task2.dao.util.SearchParameter;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Slf4j
@Transactional
public class CriteriaTourDaoTest {

    @Autowired
    private TourDao tourDao;

    private Tour expected;

    @Before
    public void setUp() {
        Country country = Country
                .builder()
                .id(22L)
                .name("Trinidad and Tobago")
                .build();
        Hotel hotel = Hotel
                .builder()
                .id(14L)
                .name("concept")
                .stars(2)
                .website("http://cbc.ca/sed/ante/vivamus.aspx")
                .latitude("25.556716")
                .longitude("110.016031")
                .features(Arrays.asList(Feature.WIFI,
                        Feature.WORK_DESK,
                        Feature.OUTLETS_GALORE,
                        Feature.BUSINESS_CENTRE))
                .build();
        expected = Tour.builder()
                .id(1L)
                .photo("http://dummyimage.com/230x135.bmp/5fa2dd/ffffff")
                .date(Date.valueOf("2017-06-19"))
                .duration(39.0)
                .description("nulla facilisi cras non velit nec nisi "
                        + "vulputate nonummy maecenas tincidunt lacus at")
                .cost(BigDecimal.valueOf(501658.5))
                .tourType(TourType.MINDFULL)
                .hotel(hotel)
                .country(country)
                .build();
    }


    @After
    public void tearDown() {
        expected = null;
    }


    @Test
    public void testReadAllTours() {
        int actual = tourDao.read().size();
        int expected = 1000;
        log.info("Tours: " + tourDao.read());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadByTourById() {
        Tour actual = tourDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testDeleteTour() {
        boolean actual = tourDao.delete(1L);
        Assert.assertTrue(actual);
    }

    @Test
    public void testSaveTour() {
        expected.setId(null);
        tourDao.create(expected);
        long id = expected.getId();
        Tour actual = tourDao.read(id);
        log.info("TOURS: " + tourDao.read());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testUpdateTour() {
        expected.setDescription("test");
        tourDao.update(expected);
        Tour actual = tourDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testReadAllUsersByTour() {
        int expectedSize = 4;
        int actual = tourDao.readAllUsers(Tour.builder().id(6L).build()).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test
    public void testReadAllReviewsByTour() {
        int expectedSize = 5;
        int actual = tourDao.readAllReviews(Tour.builder().id(3L).build()).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTextValidationConstraintException() {
        expected.setId(null);
        expected.setPhoto("");
        tourDao.create(expected);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testPositiveCostValidationConstraintException() {
        expected.setId(null);
        expected.setCost(BigDecimal.valueOf(-36.02));
        tourDao.create(expected);
    }

    @Test
    public void testFindByAllCriteria() {

        List<SearchParameter> parameters = new ArrayList<>();

        SearchParameter country = SearchParameter.COUNTRY;
        country.setValue(expected.getCountry().getName());

        SearchParameter date = SearchParameter.TOUR_DATE;
        date.setValue(expected.getDate());

        SearchParameter duration = SearchParameter.TOUR_DURATION;
        duration.setValue(expected.getDuration());

        SearchParameter tourType = SearchParameter.TOUR_TYPE;
        tourType.setValue(expected.getTourType());

        SearchParameter minCost = SearchParameter.TOUR_MIN_COST;
        minCost.setValue(expected.getCost());

        SearchParameter maxCost = SearchParameter.TOUR_MAX_COST;
        maxCost.setValue(expected.getCost());

        SearchParameter hotelStars = SearchParameter.HOTEL_STARS;
        hotelStars.setValue(expected.getHotel().getStars());

        parameters.add(country);
        parameters.add(date);
        parameters.add(duration);
        parameters.add(tourType);
        parameters.add(minCost);
        parameters.add(maxCost);
//        parameters.add(hotelStars);
        List<Tour> actual = tourDao.findByCriteria(parameters);
        Assert.assertEquals(expected.toString(), actual.get(0).toString());
    }

    @Test
    public void testFindByTourTypeCriteria() {
        int expectedSize = 108;
        List<SearchParameter> parameters = new ArrayList<>();

        SearchParameter tourType = SearchParameter.TOUR_TYPE;
        tourType.setValue(expected.getTourType());
        parameters.add(tourType);

        int actual = tourDao.findByCriteria(parameters).size();
        Assert.assertEquals(expectedSize, actual);
    }
}