package com.epam.task3.service.impl;

import com.epam.task3.dao.TourDao;
import com.epam.task3.dao.util.SearchParameter;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import com.epam.task3.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@LogExecutionTime
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
    public List<Tour> readPaginatedList(int firstResult, int maxResults) {
        return tourDao.readPaginatedList(firstResult, maxResults);
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

    @Override
    public long getCountRows() {
        return tourDao.getCountRows();
    }
}
