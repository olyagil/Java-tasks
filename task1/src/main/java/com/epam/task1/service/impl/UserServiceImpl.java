package com.epam.task1.service.impl;

import com.epam.task1.dao.UserDao;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;
import com.epam.task1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> read() throws DataBaseException {
        return userDao.read();
    }

    @Override
    public boolean create(final User user) throws DataBaseException {
        if (userDao.read(user.getLogin()) == null) {
            return userDao.create(user);
        } else {
            logger.error("Can't insert user to database");
            throw new DataBaseException();
        }
    }

    @Override
    public User read(final Long id) throws DataBaseException {
        return userDao.read(id);
    }

    @Override
    public boolean update(final User user) throws DataBaseException {
        return userDao.update(user);
    }

    @Override
    public boolean delete(final Long id) throws DataBaseException {
        return userDao.delete(id);
    }

    @Override
    public User read(final String login) throws DataBaseException {
        return userDao.read(login);
    }

    @Override
    public List<Tour> readAllToursByUser(User user) {
        return userDao.readAllTours(user);
    }
}
