package com.epam.task2.service.impl;

import com.epam.task2.annotation.LogExecutionTime;
import com.epam.task2.dao.TourDao;
import com.epam.task2.dao.util.SearchParameter;
import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;
import com.epam.task2.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@LogExecutionTime
public class TourServiceImpl implements TourService {


    @Autowired
    private TourDao tourDao;

    @Override
    public List<Tour> read() {
        return tourDao.read();
    }

    @Override
    public long create(final Tour tour) {
        return tourDao.create(tour);
    }

    @Override
    public Tour read(final Long id) {
        return tourDao.read(id);
    }

    @Override
    public boolean update(final Tour tour) {
        return tourDao.update(tour);
    }

    @Override
    public boolean delete(final Long id) {
        return tourDao.delete(id);
    }

    @Override
    public List<User> readAllUsersByTour(Tour tour) {
        return tourDao.readAllUsers(tour);
    }

    @Override
    public List<Review> readAllReviews(Tour tour) {
        return tourDao.readAllReviews(tour);
    }

    @Override
    public List<Tour> findByCriteria(List<SearchParameter> parameters) {
        return tourDao.findByCriteria(parameters);
    }
}
