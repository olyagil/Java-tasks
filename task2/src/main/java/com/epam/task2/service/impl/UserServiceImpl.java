package com.epam.task2.service.impl;

import com.epam.task2.annotation.LogExecutionTime;
import com.epam.task2.dao.UserDao;
import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;
import com.epam.task2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@LogExecutionTime
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> read() {
        return userDao.read();
    }

    @Override
    public long create(final User user) {
        return userDao.create(user);

    }

    @Override
    public User read(final Long id) {
        return userDao.read(id);
    }

    @Override
    public boolean update(final User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(final Long id) {
        return userDao.delete(id);
    }

    @Override
    public User read(final String login) {
        return userDao.read(login);
    }

    @Override
    public List<Tour> readAllToursByUser(User user) {
        return userDao.readAllTours(user);
    }

    @Override
    public List<Review> readAllReviews(User user) {
        return userDao.readAllReviews(user);
    }
}
