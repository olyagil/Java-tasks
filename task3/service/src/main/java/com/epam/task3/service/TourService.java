package com.epam.task3.service;


import com.epam.task3.dao.util.SearchParameter;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;

import java.util.List;

public interface TourService extends DataService<Tour> {

    List<Tour> readPaginatedList(int firstResult, int maxResults);

    List<User> readAllUsersByTour(Tour tour);

    List<Review> readAllReviews(Tour tour);

    List<Tour> findByCriteria(List<SearchParameter> parameters);

    long getCountRows();
}
