package com.epam.task1.dao;

import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;

import java.util.List;

public interface TourDao extends Dao<Tour> {

        List<User> readAllUsers(Tour tour);

}
