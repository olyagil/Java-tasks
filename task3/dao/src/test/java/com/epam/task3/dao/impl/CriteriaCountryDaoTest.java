package com.epam.task3.dao.impl;

import com.epam.task3.config.AppConfig;
import com.epam.task3.dao.CountryDao;
import com.epam.task3.entity.Country;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Slf4j
@Transactional
public class CriteriaCountryDaoTest {

    @Autowired
    private CountryDao countryDao;

    private Country expected;

    @Before
    public void setUp() {
        expected = Country
                .builder()
                .id(1L)
                .name("Costa Rica")
                .build();
    }

    @After
    public void tearDown() {
        expected = null;
    }

    @Test
    public void testReadAllCountry() {
        int actual = countryDao.read().size();
        int expected = 26;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadCountryById() {
        Country actual = countryDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteCountry() {
        boolean actual = countryDao.delete(expected.getId());
        Assert.assertTrue(actual);

    }

    @Test
    public void testReadByName() {
        Country actual = countryDao.read(expected.getName());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateCountry() {
        Country expected = Country
                .builder()
                .name("Test")
                .build();
        countryDao.create(expected);
        Country actual = countryDao.read(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCountry() {
        expected.setName("test");
        countryDao.update(expected);
        Country actual = countryDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected, actual);
    }

//    @Test(expected = ConstraintViolationException.class)
//    public void whenConstraintExceptionThrown_thenExpectationSatisfied() {
//        Country expected = Country
//                .builder()
//                .name("")
//                .build();
//        countryDao.create(expected);
//    }
}