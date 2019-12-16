package com.epam.task1.service.impl;

import com.epam.task1.dao.CountryDao;
import com.epam.task1.entity.Country;
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

public class CountryServiceImplTest {
    @Autowired
    @InjectMocks
    private CountryServiceImpl service;

    @Mock
    private CountryDao countryDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readById() throws DataBaseException {
        long id = 1L;
        Country expected = new Country(id, "test");
        when(countryDao.read(id)).thenReturn(expected);
        Country actual = service.read(id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void create() throws DataBaseException {
        boolean expected = true;
        when(countryDao.create(any(Country.class))).thenReturn(expected);
        boolean actual = service.create(new Country());
        Assert.assertEquals(expected, actual);
    }

    //Throwing an exception from the mocked method
    @Test(expected = DataBaseException.class)
    public void testAddService_throwsException() throws DataBaseException {
        when(countryDao.create(any(Country.class))).thenThrow(DataBaseException.class);
        Country country = new Country();
        service.create(country);
    }

    @Test
    public void readAll() throws DataBaseException {
        List<Country> countries = Mockito.spy(new ArrayList<>());
        int expected = 25;
        Mockito.doReturn(expected).when(countries).size();
        when(countryDao.read()).thenReturn(countries);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        boolean expected = true;
        long id = countryDao.read().size();
        Country country = new Country(id, "test");
        when(countryDao.update(any(Country.class))).thenReturn(expected);
        boolean actual = countryDao.update(country);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DataBaseException {
        boolean expected = true;
        when(countryDao.delete(any())).thenReturn(expected);
        boolean actual = service.delete(1L);
        Assert.assertEquals(expected, actual);
    }

}