package impl;

import com.epam.task3.dao.UserDao;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import com.epam.task3.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Slf4j
public class UserServiceImplTest {
    @Autowired
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserDao userDao;

    private User expected;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected = User.builder().id(1L).login("test").build();
    }

    @Test
    public void testReadAllUsers() {
        List<User> users = spy(new ArrayList<>());
        int expected = 25;
        doReturn(expected).when(users).size();
        when(userDao.read()).thenReturn(users);
        int actual = service.read().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCreateUser() {
        when(userDao.create(any(User.class))).thenReturn(1L);
        Long actual = service.create(expected);
        Assert.assertEquals(expected.getId(), actual);
    }

    @Test
    public void testReadUserById() {
        when(userDao.read(anyLong())).thenReturn(expected);
        User actual = service.read(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadUserByLogin() {
        when(userDao.read(anyString())).thenReturn(expected);
        User actual = service.read(expected.getLogin());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateUser() {
        when(userDao.update(expected)).thenReturn(true);
        boolean actual = service.update(expected);
        Assert.assertTrue(actual);
    }

    @Test
    public void testDeleteUser() {
        when(userDao.delete(anyLong())).thenReturn(true);
        boolean actual = service.delete(expected.getId());
        Assert.assertTrue(actual);
    }

    @Test
    public void readAllToursByUser() {
        List<Tour> tours = spy(new ArrayList<>());
        int expectedSize = 5;
        doReturn(expectedSize).when(tours).size();
        when(userDao.readAllTours(expected)).thenReturn(tours);
        int actual = service.readAllTours(expected).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test
    public void readAllReviewsByUser() {
        List<Review> users = spy(new ArrayList<>());
        int expectedSize = 5;
        doReturn(expectedSize).when(users).size();
        when(userDao.readAllReviews(expected)).thenReturn(users);
        int actual = service.readAllReviews(expected).size();
        Assert.assertEquals(expectedSize, actual);
    }

    @Test(expected = PersistenceException.class)
    public void testLoginPersistenceException() {
        when(userDao.create(any(User.class)))
                .thenThrow(PersistenceException.class);
        service.create(new User());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTextValidationConstraintException() {
        when(userDao.create(any(User.class)))
                .thenThrow(ConstraintViolationException.class);
        service.create(new User());
    }
}