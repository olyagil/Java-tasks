package com.epam.task3.service.impl;

import com.epam.task3.dao.UserDao;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import com.epam.task3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@LogExecutionTime
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
    public List<User> readPaginatedList(int pageNumber, int maxResults) {
        return userDao.readPaginatedList(pageNumber, maxResults);
    }

    @Override
    public long getCountRows() {
        return userDao.getCountRows();
    }

    @Override
    public User read(final String login) {
        return userDao.read(login);
    }

    @Override
    public User read(String login, String password) {
        return userDao.read(login, password);
    }

    @Override
    public List<Tour> readAllTours(User user) {
        return userDao.readAllTours(user);
    }

    @Override
    public List<Review> readAllReviews(User user) {
        return userDao.readAllReviews(user);
    }
}
