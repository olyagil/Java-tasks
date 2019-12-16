package com.epam.task1.service;

import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;

import java.util.List;

public interface TourService extends DataService<Tour> {

    List<User> readAllUsersByTour(Tour tour);
}
