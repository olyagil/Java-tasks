package com.epam.task1.service.impl;

import com.epam.task1.dao.TourDao;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;
import com.epam.task1.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tourService")
public class TourServiceImpl implements TourService {


    @Autowired
    private TourDao tourDao;

    @Override
    public List<Tour> read() throws DataBaseException {
        return tourDao.read();
    }

    @Override
    public boolean create(final Tour tour) throws DataBaseException {
        return tourDao.create(tour);
    }

    @Override
    public Tour read(final Long id) throws DataBaseException {
        return tourDao.read(id);
    }

    @Override
    public boolean update(final Tour tour) throws DataBaseException {
        return tourDao.update(tour);
    }

    @Override
    public boolean delete(final Long id) throws DataBaseException {
        return tourDao.delete(id);
    }

    @Override
    public List<User> readAllUsersByTour(Tour tour) {
        return tourDao.readAllUsers(tour);
    }
}
