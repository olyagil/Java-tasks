package com.epam.task3.service.impl;

import com.epam.task3.dao.ReviewDao;
import com.epam.task3.entity.Review;
import com.epam.task3.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public List<Review> read() {
        return reviewDao.read();
    }

    @Override
    public long create(final Review review) {
        return reviewDao.create(review);
    }

    @Override
    public Review read(final Long id) {
        return reviewDao.read(id);
    }

    @Override
    public boolean update(final Review review) {
        return reviewDao.update(review);
    }

    @Override
    public boolean delete(final Long id) {
        return reviewDao.delete(id);
    }

    @Override
    public long getCountRows() {
        return reviewDao.getCountRows();
    }

    @Override
    public List<Review> readPaginatedList(int page, int maxResults) {
        return reviewDao.readPaginatedList(page, maxResults);
    }
}
