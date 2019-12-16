package com.epam.task4.service;

import com.epam.task4.entity.Review;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    Review findBy_id(ObjectId id);

    void save(Review review);

    void delete(Review review);
}
