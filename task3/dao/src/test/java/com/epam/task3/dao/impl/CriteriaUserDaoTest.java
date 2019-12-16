package com.epam.task3.dao.impl;

import com.epam.task3.config.AppConfig;
import com.epam.task3.dao.UserDao;
import com.epam.task3.entity.User;
import com.epam.task3.entity.enumerution.Roles;
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

import javax.persistence.PersistenceException;

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
                .login("admin")
                .password("$2a$10$7.urGoA8z/7l267ge8wXk.FDokBUV9.crGrhpeRzWYLTUghKtIXxS")
                .role(Roles.ADMIN)
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
        User expected =
                User.builder().login("test@com").password("test").role(Roles.USER).build();
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
        userDao.create(expected);
    }

    @Test(expected = PersistenceException.class)
    public void testTextValidationConstraintException() {
        expected.setId(null);
        expected.setPassword("");
        userDao.create(expected);
    }
}