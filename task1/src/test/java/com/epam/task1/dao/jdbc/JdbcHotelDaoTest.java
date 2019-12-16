package com.epam.task1.dao.jdbc;

import com.epam.task1.config.AppConfig;
import com.epam.task1.entity.Hotel;
import com.epam.task1.entity.enumerution.Feature;
import com.epam.task1.exception.DataBaseException;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class JdbcHotelDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUserDaoTest.class);

    @Autowired
    private JdbcHotelDao hotelDao;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test()
    public void create() throws DataBaseException {
        Hotel actual = new Hotel("test", 5,
                "http:/test.com/", "9.7494365",
                "105.5327831", Feature.BUSINESS_CENTRE);
        hotelDao.create(actual);
        List<Hotel> hotels = hotelDao.read();
        int id = hotels.size();
        actual.setId((long) id);
        Hotel expected = hotels.get(id - 1);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void readAll() throws DataBaseException {
        int actual = hotelDao.read().size();
        int expected = 100;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void readById() throws DataBaseException {
        Hotel actual = new Hotel("test", 5,
                "http:/test.com/", "9.7494365",
                "105.5327831", Feature.BUSINESS_CENTRE);
        hotelDao.create(actual);
        List<Hotel> hotels = hotelDao.read();
        long id = hotels.size();
        actual.setId( id);
        Hotel expected = hotelDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        long id = hotelDao.read().size();
        Hotel actual = new Hotel(id, "test", 5,
                "http:/test.com/", "9.7494365",
                "105.5327831", Feature.BUSINESS_CENTRE);
        hotelDao.update(actual);
        Hotel expected = hotelDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test()
    public void delete() throws DataBaseException {
        long size = hotelDao.read().size();
        hotelDao.delete(size);
        long expected = hotelDao.read().size();
        long actual = size - 1;
        Assert.assertEquals(expected, actual);
    }
}