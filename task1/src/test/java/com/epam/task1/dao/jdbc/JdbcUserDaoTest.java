package com.epam.task1.dao.jdbc;

import com.epam.task1.config.AppConfig;
import com.epam.task1.entity.User;
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
public class JdbcUserDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcUserDaoTest.class);

    @Autowired
    private JdbcUserDao userDao;

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
        User actual = new User("test", "test");
        userDao.create(actual);
        List<User> users = userDao.read();
        User expected = users.get(users.size() - 1);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void readAll() {
        int expected = userDao.read().size();
        int actual = 100;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void readById() throws DataBaseException {
        User actual = new User("test", "test");
        userDao.create(actual);
        Assert.assertEquals(userDao.read(101L), actual);
    }

    @Test
    public void update() {
        long id = userDao.read().size();
        User actual = new User(id, "test", "test");
        userDao.update(actual);
        User expected = userDao.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test()
    public void delete() {
        long size = userDao.read().size();
        userDao.delete(size);
        long expected = userDao.read().size();
        long actual = size - 1;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DataBaseException.class)
    public void createWithSameLogin() throws DataBaseException {
        String login = "nunc@gravidanuncsed.co.uk";
        userDao.create(new User(login, "test"));
    }

    @Test
    public void readAllToursByUser() {
        long id = 1L;
        User user = new User(id);
        int expected = 14;
        int actual = userDao.readAllTours(user).size();
        System.out.println(userDao.readAllTours(user));
        Assert.assertEquals(expected, actual);
    }
}