package com.epam.task1.dao.jdbc;

import com.epam.task1.config.AppConfig;
import com.epam.task1.entity.Review;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class JdbcReviewDaoTest {

    @Autowired
    private JdbcReviewDao reviewDao;

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

    @Test
    public void create() throws DataBaseException {
        Review expected = new Review(Date.valueOf("2018-02-02"), "Test",
                new User(1L), new Tour(1L));
        reviewDao.create(expected);
        long id = reviewDao.read().size();
        expected.setId(id);
        Review actual = reviewDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAll() throws DataBaseException {
        int actual = reviewDao.read().size();
        int expected = 1000;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readById() throws DataBaseException {
        Review expected = new Review(Date.valueOf("2018-02-02"), "Test",
                new User(1L), new Tour(1L));
        reviewDao.create(expected);
        long id = reviewDao.read().size();
        expected.setId(id);
        Review actual = reviewDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        long id = 1L;
        Review expected = new Review(id, Date.valueOf("2018-02-02"), "Test",
                new User(1L), new Tour(1L));
        reviewDao.update(expected);
        Review actual = reviewDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DataBaseException {
        long size = reviewDao.read().size();
        reviewDao.delete(size);
        long expected = size - 1;
        long actual = reviewDao.read().size();
        Assert.assertEquals(expected, actual);
    }
}