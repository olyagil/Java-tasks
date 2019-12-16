package com.epam.task2.service.impl;

import com.epam.task2.dao.HotelDao;
import com.epam.task2.entity.Country;
import com.epam.task2.entity.Hotel;
import com.epam.task2.entity.enumerution.Feature;
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

public class HotelServiceImplTest {
    @Autowired
    @InjectMocks
    private HotelServiceImpl service;

    @Mock
    private HotelDao hotelDao;

    private Hotel expected;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected = Hotel
                .builder()
                .id(1L)
                .build();
        ;
    }

    @Test
    public void testReadAllHotels() {
        List<Hotel> hotels = spy(new ArrayList<>());
        int expected = 100;
        doReturn(expected).when(hotels).size();
        when(hotelDao.read()).thenReturn(hotels);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateHotel() {
        when(hotelDao.create(any(Hotel.class))).thenReturn(1L);
        Long actual = service.create(expected);
        Assert.assertEquals(expected.getId(), actual);
    }

    @Test
    public void testReadHotelById() {
        when(hotelDao.read(anyLong())).thenReturn(expected);
        Hotel actual = service.read(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateHotel() {
        when(hotelDao.update(expected)).thenReturn(true);
        boolean actual = service.update(expected);
        Assert.assertTrue(actual);
    }

    @Test
    public void testDeleteHotel() {
        when(hotelDao.delete(anyLong())).thenReturn(true);
        boolean actual = service.delete(expected.getId());
        Assert.assertTrue(actual);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNameValidationConstraintException() {
        when(hotelDao.create(any(Hotel.class)))
                .thenThrow(ConstraintViolationException.class);
        service.create(expected);
    }
}