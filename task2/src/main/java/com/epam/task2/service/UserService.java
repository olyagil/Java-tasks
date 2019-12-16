package com.epam.task2.service;

import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;

import java.util.List;

public interface UserService extends DataService<User> {

    User read(String login);

    List<Tour> readAllToursByUser(User user);

    List<Review> readAllReviews(User user);
}
