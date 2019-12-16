package com.epam.task3.dao;

import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> readPaginatedList(int pageNumber, int maxResults);

    long getCountRows();

    User read(String login);

    User read(String login, String password);

    List<Tour> readAllTours(User user);

    List<Review> readAllReviews(User user);
}
