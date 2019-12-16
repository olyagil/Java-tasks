package com.epam.task2.service;


import com.epam.task2.dao.util.SearchParameter;
import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;

import java.util.List;

public interface TourService extends DataService<Tour> {

    List<User> readAllUsersByTour(Tour tour);

    List<Review> readAllReviews(Tour tour);

    List<Tour> findByCriteria(List<SearchParameter> parameters);

}
