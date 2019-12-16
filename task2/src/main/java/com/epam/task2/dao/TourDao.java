package com.epam.task2.dao;

import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;
import com.epam.task2.dao.util.SearchParameter;

import java.util.List;

public interface TourDao extends Dao<Tour> {

    List<User> readAllUsers(Tour tour);

    List<Review> readAllReviews(Tour tour);

    List<Tour> findByCriteria(List<SearchParameter> parameters);
}
