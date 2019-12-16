package com.epam.task1.service.impl;

import com.epam.task1.dao.TourDao;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TourServiceImplTest {
    @Autowired
    @InjectMocks
    private TourServiceImpl service;

    @Mock
    private TourDao tourDao;
    private static final Logger logger = LoggerFactory.getLogger(TourServiceImplTest.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readById() throws DataBaseException {
        long id = 1L;
        Tour expected = new Tour();
        when(tourDao.read(id)).thenReturn(expected);
        Tour actual = service.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() throws DataBaseException {
        boolean expected = true;
        when(tourDao.create(any(Tour.class))).thenReturn(expected);
        boolean actual = service.create(new Tour());
        Assert.assertEquals(expected, actual);
    }

    //Throwing an exception from the mocked method
    @Test(expected = DataBaseException.class)
    public void testAddCustomer_throwsException() throws DataBaseException {
        when(tourDao.create(any(Tour.class))).thenThrow(DataBaseException.class);
        Tour tour = new Tour();
        service.create(tour);
    }

    @Test
    public void readAll() throws DataBaseException {
        List<Tour> tours = Mockito.spy(new ArrayList<>());
        int expected = 25;
        Mockito.doReturn(expected).when(tours).size();
        when(tourDao.read()).thenReturn(tours);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        boolean expected = true;
        Tour country = new Tour();
        when(tourDao.update(any(Tour.class))).thenReturn(expected);
        boolean actual = tourDao.update(country);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DataBaseException {
        boolean expected = true;
        when(tourDao.delete(any())).thenReturn(expected);
        boolean actual = service.delete(1L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void readAllToursByUser() throws DataBaseException {
        List<User> users = Mockito.spy(new ArrayList<>());
        int expected = 4;
        Mockito.doReturn(expected).when(users).size();
        when(tourDao.readAllUsers(any(Tour.class))).thenReturn(users);
        int actual = service.readAllUsersByTour(new Tour()).size();
        Assert.assertEquals(expected, actual);
    }
}