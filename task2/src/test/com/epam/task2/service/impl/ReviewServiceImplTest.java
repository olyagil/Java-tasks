package com.epam.task2.service.impl;

import com.epam.task2.dao.ReviewDao;
import com.epam.task2.entity.*;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Slf4j

public class ReviewServiceImplTest {
    @Autowired
    @InjectMocks
    private ReviewServiceImpl service;

    @Mock
    private ReviewDao reviewDao;

    private Review expected;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected = Review.builder().id(1L).build();
    }


    @Test
    public void testReadAllReviews() {
        List<Review> reviews = spy(new ArrayList<>());
        int expected = 1000;
        doReturn(expected).when(reviews).size();
        when(reviewDao.read()).thenReturn(reviews);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateReview() {
        when(reviewDao.create(any(Review.class))).thenReturn(1L);
        Long actual = service.create(expected);
        Assert.assertEquals(expected.getId(), actual);
    }

    @Test
    public void testReadHotelById() {
        when(reviewDao.read(anyLong())).thenReturn(expected);
        Review actual = service.read(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateReview() {
        when(reviewDao.update(expected)).thenReturn(true);
        boolean actual = service.update(expected);
        Assert.assertTrue(actual);
    }

    @Test
    public void testDeleteReview() {
        when(reviewDao.delete(anyLong())).thenReturn(true);
        boolean actual = service.delete(expected.getId());
        Assert.assertTrue(actual);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTextValidationConstraintException() {
        when(reviewDao.create(any(Review.class))).thenThrow(ConstraintViolationException.class);
        service.create(expected);
    }
}