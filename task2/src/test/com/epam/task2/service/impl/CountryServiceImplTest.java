package com.epam.task2.service.impl;

import com.epam.task2.dao.CountryDao;
import com.epam.task2.entity.Country;
import com.epam.task2.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@Slf4j
public class CountryServiceImplTest {

    @Autowired
    @InjectMocks
    private CountryServiceImpl service;

    @Mock
    private CountryDao countryDao;

    private Country expected;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected = Country.builder().id(1L).name("test").build();
    }


    @Test
    public void testReadAllCountries() {
        List<Country> countries = spy(new ArrayList<>());
        int expected = 25;
        doReturn(expected).when(countries).size();
        when(countryDao.read()).thenReturn(countries);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateCountry() {
        when(countryDao.create(any(Country.class))).thenReturn(1L);
        Long actual = service.create(expected);
        Assert.assertEquals(expected.getId(), actual);
    }

    @Test
    public void testReadCountryById() {
        when(countryDao.read(anyLong())).thenReturn(expected);
        Country actual = countryDao.read(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadCountryByName() {
        when(countryDao.read(anyString())).thenReturn(expected);
        Country actual = service.read(expected.getName());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCountry() {
        when(countryDao.update(expected)).thenReturn(true);
        boolean actual = service.update(expected);
        Assert.assertTrue(actual);
    }

    @Test
    public void testDeleteCountry() {
        when(countryDao.delete(anyLong())).thenReturn(true);
        boolean actual = service.delete(expected.getId());
        Assert.assertTrue(actual);
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenConstraintExceptionThrown_thenExpectationSatisfied() {
        when(countryDao.create(any(Country.class)))
                .thenThrow(ConstraintViolationException.class);
        service.create(new Country());
    }
}