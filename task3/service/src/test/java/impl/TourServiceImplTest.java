package impl;

import com.epam.task3.dao.TourDao;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import com.epam.task3.service.impl.TourServiceImpl;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Slf4j
public class TourServiceImplTest {

    @Autowired
    @InjectMocks
    private TourServiceImpl service;

    @Mock
    private TourDao tourDao;

    private Tour expected;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected = Tour.builder().id(1L).build();
    }

    @Test
    public void testReadAllTours() {
        List<Tour> tours = spy(new ArrayList<>());
        int expected = 1000;
        doReturn(expected).when(tours).size();
        when(tourDao.read()).thenReturn(tours);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateTour() {
        when(tourDao.create(any(Tour.class))).thenReturn(1L);
        Long actual = service.create(expected);
        Assert.assertEquals(expected.getId(), actual);
    }

    @Test
    public void testReadTourById() {
        when(tourDao.read(anyLong())).thenReturn(expected);
        Tour actual = service.read(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateTour() {
        when(tourDao.update(expected)).thenReturn(true);
        boolean actual = service.update(expected);
        Assert.assertTrue(actual);
    }

    @Test
    public void testDeleteTour() {
        when(tourDao.delete(anyLong())).thenReturn(true);
        boolean actual = service.delete(expected.getId());
        Assert.assertTrue(actual);
    }

    @Test
    public void readAllUsersByTour() {
        List<User> users = spy(new ArrayList<>());
        int expectedSize = 5;
        doReturn(expectedSize).when(users).size();
        when(tourDao.readAllUsers(expected)).thenReturn(users);
        int actual = service.readAllUsersByTour(expected).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test
    public void readAllReviewsByTour() {
        List<Review> reviews = spy(new ArrayList<>());
        int expectedSize = 4;
        doReturn(expectedSize).when(reviews).size();
        when(tourDao.readAllReviews(expected)).thenReturn(reviews);
        int actual = service.readAllReviews(expected).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test
    public void findByAllCriteria() {
        List<Tour> reviews = spy(new ArrayList<>());
        int expectedSize = 4;
        doReturn(expectedSize).when(reviews).size();
        when(tourDao.findByCriteria(anyList())).thenReturn(reviews);
        int actual = service.findByCriteria(new ArrayList<>()).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTextValidationConstraintException() {
        when(tourDao.create(any(Tour.class)))
                .thenThrow(ConstraintViolationException.class);
        service.create(new Tour());
    }
}
