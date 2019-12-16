package com.epam.task1.service.impl;

import com.epam.task1.dao.HotelDao;
import com.epam.task1.entity.Country;
import com.epam.task1.entity.Hotel;
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

public class HotelServiceImplTest {

    @Autowired
    @InjectMocks
    private HotelServiceImpl service;

    @Mock
    private HotelDao hotelDao;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readById() throws DataBaseException {
        long id = 1L;
        Hotel expected = new Hotel();
        when(hotelDao.read(id)).thenReturn(expected);
        Hotel actual = service.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() throws DataBaseException {
        boolean expected = true;
        when(hotelDao.create(any(Hotel.class))).thenReturn(expected);
        boolean actual = service.create(new Hotel());
        Assert.assertEquals(expected, actual);
    }

    //Throwing an exception from the mocked method
    @Test(expected = DataBaseException.class)
    public void testAddCustomer_throwsException() throws DataBaseException {
        when(hotelDao.create(any(Hotel.class))).thenThrow(DataBaseException.class);
        Hotel hotel = new Hotel();
        service.create(hotel);
    }

    @Test
    public void readAll() throws DataBaseException {
        List<Hotel> hotels = Mockito.spy(new ArrayList<>());
        int expected = 100;
        Mockito.doReturn(expected).when(hotels).size();
        when(hotelDao.read()).thenReturn(hotels);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        boolean expected = true;
        Hotel hotel = new Hotel();
        when(hotelDao.update(any(Hotel.class))).thenReturn(expected);
        boolean actual = hotelDao.update(hotel);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DataBaseException {
        boolean expected = true;
        when(hotelDao.delete(any())).thenReturn(expected);
        boolean actual = service.delete(1L);
        Assert.assertEquals(expected, actual);
    }
}