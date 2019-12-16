package com.epam.task2.dao.criteria;

import com.epam.task2.config.AppConfig;
import com.epam.task2.dao.HotelDao;
import com.epam.task2.entity.Hotel;
import com.epam.task2.entity.enumerution.Feature;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
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
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Slf4j
@Transactional
public class CriteriaHotelDaoTest {

    @Autowired
    private HotelDao hotelDao;

    private Hotel expected;

    @Before
    public void setUp() {
        expected = Hotel
                .builder()
                .id(1L)
                .name("framework")
                .stars(2)
                .website("https://oaic.gov.au/quis/lectus.xml")
                .latitude("-23.1072154")
                .longitude("-48.925516")
                .features(Arrays.asList(Feature.WIFI,
                        Feature.WORK_DESK,
                        Feature.OUTLETS_GALORE,
                        Feature.BUSINESS_CENTRE))
                .build();
    }

    @After
    public void tearDown() {
        expected = null;
    }


    @Test
    public void testReadAllHotels() {
        int actual = hotelDao.read().size();
        int expected = 101;
        log.info("Hotel: " + hotelDao.read());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadHotelById() {
        Hotel actual = hotelDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testCreateHotel() {
        expected.setId(null);
        hotelDao.create(expected);
        long id = expected.getId();
        Hotel actual = hotelDao.read(id);
        log.info("hotel: " + actual);
        log.info("hotel's features "  + actual.getFeatures() );
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testUpdateHotel() {
        expected.setName("test");
        hotelDao.update(expected);
        Hotel actual = hotelDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testDeleteHotel() {
        boolean actual = hotelDao.delete(expected.getId());
        Assert.assertTrue(actual);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNameValidationConstraintException() {
        expected.setId(null);
        expected.setName("");
        hotelDao.create(expected);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testStarsValidationConstraintExceptionThrown() {
        expected.setId(null);
        expected.setStars(11);
        hotelDao.create(expected);

    }
}