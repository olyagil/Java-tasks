package com.epam.task2.dao.criteria;

import com.epam.task2.config.AppConfig;
import com.epam.task2.dao.UserDao;
import com.epam.task2.entity.User;
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

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Slf4j
@Transactional
public class CriteriaUserDaoTest {

    @Autowired
    private UserDao userDao;

    private User expected;

    @Before
    public void setUp() {
        expected = User
                .builder()
                .id(1L)
                .login("nunc@gravidanuncsed.co.uk")
                .password("2854")
                .build();
    }

    @After
    public void tearDown() {
        expected = null;
    }


    @Test
    public void testReadAllUsers() {
        int actual = userDao.read().size();
        int expected = 100;
        log.info("Users: " + userDao.read());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadUserById() {
        User actual = userDao.read(expected.getId());
        log.info("EXPECTED: " + expected);
        log.info("ACTUAL: " + actual);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testDeleteUser() {
        boolean actual = userDao.delete(expected.getId());
        Assert.assertTrue(actual);

    }

    @Test
    public void testReadUserByLogin() {
        User actual = userDao.read(expected.getLogin());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testCreateUser() {
        User expected = User.builder().login("test@com").password("test").build();
        userDao.create(expected);
        User actual = userDao.read(expected.getId());
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testUpdateUser() {
        expected.setPassword("test");
        userDao.update(expected);
        User actual = userDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testReadAllTours() {
        int expectedSize = 14;
        int actual = userDao.readAllTours(expected).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test
    public void testReadAllReviews() {
        int expectedSize = 9;
        int actual = userDao.readAllReviews(expected).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test(expected = PersistenceException.class)
    public void testLoginPersistenceException() {
        expected.setId(null);
        expected.setLogin("nunc@gravidanuncsed.co.uk");
        userDao.create(expected);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTextValidationConstraintException() {
        expected.setId(null);
        expected.setPassword("");
        userDao.create(expected);
    }
}