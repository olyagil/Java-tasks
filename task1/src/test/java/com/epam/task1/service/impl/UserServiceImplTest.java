package com.epam.task1.service.impl;

import com.epam.task1.dao.UserDao;
import com.epam.task1.entity.Country;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Autowired
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readById() throws DataBaseException {
        long id = 65L;
        User expected = new User(id, "test", "test");
        when(userDao.read(id)).thenReturn(expected);
        User actual = service.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() throws DataBaseException {
        boolean expected = true;
        when(userDao.create(any(User.class))).thenReturn(expected);
        boolean actual = service.create(new User());
        Assert.assertEquals(expected, actual);
    }

    //Throwing an exception from the mocked method
    @Test(expected = RuntimeException.class)
    public void testAddCustomer_throwsException() throws DataBaseException {
        when(userDao.create(any(User.class))).thenThrow(RuntimeException.class);
        User user = new User();
        service.create(user);
    }

    @Test
    public void readAll() throws DataBaseException {
        List<User> users = Mockito.spy(new ArrayList<>());
        int expected = 25;
        Mockito.doReturn(expected).when(users).size();
        when(userDao.read()).thenReturn(users);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        boolean expected = true;
        long id = userDao.read().size();
        User user = new User(id, "test", "test");
        when(userDao.update(any(User.class))).thenReturn(expected);
        boolean actual = userDao.update(user);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DataBaseException {
        boolean expected = true;
        when(userDao.delete(any())).thenReturn(expected);
        boolean actual = service.delete(1L);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DataBaseException.class)
    public void createWithSameLogin() throws DataBaseException {
        when(userDao.create(any(User.class))).thenThrow(DataBaseException.class);
        boolean actual = service.create(new User("nunc@gravidanuncsed.co.uk",
                "test"));
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAllToursByUser() throws DataBaseException {
        List<Tour> tours = Mockito.spy(new ArrayList<>());
        int expected = 14;
        Mockito.doReturn(expected).when(tours).size();
        when(userDao.readAllTours(any(User.class))).thenReturn(tours);
        int actual = service.readAllToursByUser(new User()).size();
        Assert.assertEquals(expected, actual);
    }

}