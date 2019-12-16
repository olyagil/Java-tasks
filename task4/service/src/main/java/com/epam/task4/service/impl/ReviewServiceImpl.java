package com.epam.task4.service.impl;

import com.epam.task4.entity.Review;
import com.epam.task4.repository.ReviewRepository;
import com.epam.task4.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    @Override
    public Review findBy_id(ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public void save(Review review) {
        repository.save(review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }
}
