package com.epam.task1.dao;

import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;

import java.util.List;

public interface UserDao extends Dao<User> {

    User read(String login) throws DataBaseException;

    List<Tour> readAllTours(User user);
}
