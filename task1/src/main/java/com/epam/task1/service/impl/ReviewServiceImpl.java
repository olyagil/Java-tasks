package com.epam.task1.service.impl;

import com.epam.task1.dao.ReviewDao;
import com.epam.task1.entity.Review;
import com.epam.task1.exception.DataBaseException;
import com.epam.task1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public List<Review> read() throws DataBaseException {
        return reviewDao.read();
    }

    @Override
    public boolean create(final Review review) throws DataBaseException {
        return reviewDao.create(review);
    }

    @Override
    public Review read(final Long id) throws DataBaseException {
        return reviewDao.read(id);
    }

    @Override
    public boolean update(final Review review) throws DataBaseException {
        return reviewDao.update(review);
    }

    @Override
    public boolean delete(final Long id) throws DataBaseException {
        return reviewDao.delete(id);
    }
}
