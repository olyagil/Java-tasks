package com.epam.task2.dao;

import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    User read(String login);

    List<Tour> readAllTours(User user);

    List<Review> readAllReviews(User user);
}
