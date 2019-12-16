package com.epam.task3.dao.impl;

import com.epam.task3.config.AppConfig;
import com.epam.task3.dao.TourDao;
import com.epam.task3.dao.util.SearchParameter;
import com.epam.task3.entity.Country;
import com.epam.task3.entity.Hotel;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.enumerution.Feature;
import com.epam.task3.entity.enumerution.TourType;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import java.util.HashSet;
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
                .id(21L)
                .name("Equatorial Guinea")
                .build();
        Hotel hotel = Hotel
                .builder()
                .id(70L)
                .name("circuit")
                .stars(4)
                .website("http://apache.org/justo/maecenas/rhoncus.jpg")
                .latitude("-8.584571")
                .longitude("116.1245642")
                .features(new HashSet<>(Arrays.asList(Feature.WIFI,
                        Feature.WORK_DESK,
                        Feature.OUTLETS_GALORE,
                        Feature.BUSINESS_CENTRE)))
                .build();
        expected = Tour.builder()
                .id(1L)
                .photo("iVBORw0KGgoAAAANSUhEUgAAABAAAA"
                        + "AQCAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29md"
                        + "HdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAHySURBVDjLpVNB"
                        + "axpBGH2z7rrb6poNG0lSi0VyiA1I6a2XUtBLyD/oyV/gvSD03Gv"
                        + "/Q7El5NZDhBDx1lPpQUyRbg9SoUYTsEs21XVdM9/QWdZePGTgY2"
                        + "beft/33pudYcvlEvcZCu45VLno9Xrni8WizANBEGA+n0fh+34Us"
                        + "9mM5la1Wq1QHSMLvPhVMplsZ7NZkfT/iNvUNA3tdhv9fr9Sq9Va"
                        + "QgFnfGtZFobDIXRdj4rihbQeDAbIZDIoFApEWudwi3U6HcFu2zZ"
                        + "c112RS1aokDGGRCIh2E3TRCqVQrPZhOM4FZXYSTp5pSTDMERI9r"
                        + "gSmsMwxHQ6RalUQrfbravE5HkexuOxOCCZRIdJs9xLjPaqqiKdT"
                        + "guV1KBM3WlDCdySkKwoyooC2Sifz4u8XC5HNWWFWMkrBX2QITGJ"
                        + "P//2CdbVT1gTB896H6JfrFIDWUQMxWIxkkysEn/y6wJP/3yFzTY"
                        + "xe5TGFceiBvQHpL+4XHl45uf3SO15sPU9oMxg9D0cOCdCHWs0Gq"
                        + "fcy2HslkUzxaF9jH3NwcbONnR3Eziii8Mb/7jF98nDS7buMf1+w"
                        + "RraG7w2sQP92gJecnCDx5jf2Hc3H9c/Jh+j5Rnwd3fELYXA5T/8"
                        + "SwC4GK1X8Jg94E9uAhNJxVeC7ewWYHDkhrcOYd0B0mCFUhT4PX8"
                        + "AAAAASUVORK5CYII=")
                .date(Date.valueOf("2019-03-05"))
                .duration(17.0)
                .description("luctus cum sociis natoque penatibus et magnis d"
                        + "is parturient montes nascetur ridiculus mus vivamus "
                        + "vestibulum sagittis sapien cum")
                .cost(BigDecimal.valueOf(667903.5))
                .tourType(TourType.RIVER_CRUISE)
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
        List<Tour> actual = tourDao.findByCriteria(parameters);
        Assert.assertEquals(expected.toString(), actual.get(0).toString());
    }

    @Test
    public void testFindByTourTypeCriteria() {
        int expectedSize = 115;
        List<SearchParameter> parameters = new ArrayList<>();

        SearchParameter tourType = SearchParameter.TOUR_TYPE;
        tourType.setValue(expected.getTourType());
        parameters.add(tourType);

        int actual = tourDao.findByCriteria(parameters).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test
    public void readPaginatedList() {
        int expectedSize = 10;
        int actual = tourDao.readPaginatedList(1, expectedSize).size();
        Assert.assertEquals(expectedSize, actual);
    }
}