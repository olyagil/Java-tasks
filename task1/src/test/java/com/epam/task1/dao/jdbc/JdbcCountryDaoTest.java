package com.epam.task1.dao.jdbc;

import com.epam.task1.config.AppConfig;
import com.epam.task1.config.TestConfig;
import com.epam.task1.entity.Country;
import com.epam.task1.exception.DataBaseException;
import org.flywaydb.core.Flyway;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class JdbcCountryDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCountryDaoTest.class);

    @Autowired
    private JdbcCountryDao countryDao;

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
        Country actual = new Country("test");
        countryDao.create(actual);
        List<Country> countries = countryDao.read();
        int id = countries.size();
        actual.setId((long) id);
        Country expected = countries.get(id - 1);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void readAll() throws DataBaseException {
        int actual = countryDao.read().size();
        int expected = 26;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void readById() throws DataBaseException {
        Country actual = new Country("test");
        countryDao.create(actual);
        List<Country> countries = countryDao.read();
        long id = countries.size();
        actual.setId(id);
        Country expected = countryDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        long id = countryDao.read().size();
        Country actual = new Country(id, "Test");
        countryDao.update(actual);
        Country expected = countryDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test()
    public void delete() throws DataBaseException {
        long size = countryDao.read().size();
        countryDao.delete(size);
        long expected = countryDao.read().size();
        long actual = size - 1;
        logger.info("actual: " + actual + " expected: " + expected);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DataBaseException.class)
    public void createWithSameNameCountry() throws DataBaseException {
        countryDao.create(new Country("Costa Rica"));
    }
}