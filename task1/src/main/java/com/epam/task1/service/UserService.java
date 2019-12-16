package com.epam.task1.service;

import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;

import java.util.List;

public interface UserService extends DataService<User> {

    User read(String login) throws DataBaseException;

    List<Tour> readAllToursByUser(User user);

}
