package com.epam.task1.dao.jdbc;

import com.epam.task1.config.AppConfig;
import com.epam.task1.entity.Country;
import com.epam.task1.entity.Hotel;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.entity.enumerution.TourType;
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

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class JdbcTourDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcTourDaoTest.class);

    @Autowired
    private JdbcTourDao tourDao;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @After
    public void tearDown() {
    }

    @Test()
    public void create() throws DataBaseException {
        Tour actual = new Tour("http://dummyimage.com/138x104.png/dddddd/000000",
                Date.valueOf("2017-08-21"), 9.0, "platea dictumst", 587711.6,
                TourType.ADVENTURE, new Hotel(4L), new Country(1L, "Costa Rica"));
        tourDao.create(actual);
        long id = tourDao.read().size();
        actual.setId(id);
        Tour expected = tourDao.read(id);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void readAll() throws DataBaseException {
        int expected = tourDao.read().size();
        int actual = 1000;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void readById() throws DataBaseException {
        Tour actual = new Tour("http://dummyimage.com/138x104.png/dddddd/000000",
                Date.valueOf("2017-08-21"), 9.0, "platea dictumst", 587711.6,
                TourType.ADVENTURE, new Hotel(4L), new Country(1L, "Costa Rica"));
        tourDao.create(actual);
        long id = tourDao.read().size();
        actual.setId(id);
        Tour expected = tourDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        long id = tourDao.read().size();
        Tour actual = new Tour(id, "http://dummyimage.com/138x104.png/dddddd/000000",
                Date.valueOf("2018-04-30"), 9.0, "platea dictumst", 587711.6,
                TourType.ADVENTURE, new Hotel(4L), new Country(1L, "Costa Rica"));
        tourDao.update(actual);
        Tour expected = tourDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test()
    public void delete() throws DataBaseException {
        long size = tourDao.read().size();
        tourDao.delete(size);
        long expected = tourDao.read().size();
        long actual = size - 1;
        logger.info("actual: " + actual + " expected: " + expected);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAllToursByUser() {
        long id = 6L;
        Tour tour = new Tour(id);
        int expected = 4;
        System.out.println(tourDao.readAllUsers(tour));
        int actual = tourDao.readAllUsers(tour).size();
        Assert.assertEquals(expected, actual);
    }
}