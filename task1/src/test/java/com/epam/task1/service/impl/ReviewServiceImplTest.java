package com.epam.task1.service.impl;

import com.epam.task1.dao.ReviewDao;
import com.epam.task1.entity.Country;
import com.epam.task1.entity.Review;
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

public class ReviewServiceImplTest {

    @Autowired
    @InjectMocks
    private ReviewServiceImpl service;

    @Mock
    private ReviewDao reviewDao;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readById() throws DataBaseException {
        long id = 1L;
        Review expected = new Review();
        when(reviewDao.read(id)).thenReturn(expected);
        Review review = service.read(id);
        Assert.assertEquals(expected, review);
    }

    @Test
    public void create() throws DataBaseException {
        boolean expected = true;
        when(reviewDao.create(any(Review.class))).thenReturn(expected);
        boolean actual = service.create(new Review());
        Assert.assertEquals(expected, actual);
    }

    //Throwing an exception from the mocked method
    @Test(expected = DataBaseException.class)
    public void testAddCustomer_throwsException() throws DataBaseException {
        when(reviewDao.create(any(Review.class))).thenThrow(DataBaseException.class);
        Review review = new Review();
        service.create(review);
    }

    @Test
    public void readAll() throws DataBaseException {
        List<Review> reviews = Mockito.spy(new ArrayList<>());
        int expected = 25;
        Mockito.doReturn(expected).when(reviews).size();
        when(reviewDao.read()).thenReturn(reviews);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update() throws DataBaseException {
        boolean expected = true;
        Review review = new Review();
        when(reviewDao.update(any(Review.class))).thenReturn(expected);
        boolean actual = reviewDao.update(review);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() throws DataBaseException {
        boolean expected = true;
        when(reviewDao.delete(any())).thenReturn(expected);
        boolean actual = service.delete(1L);
        Assert.assertEquals(expected, actual);
    }


}