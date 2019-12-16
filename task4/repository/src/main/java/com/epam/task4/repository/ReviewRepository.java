package com.epam.task4.repository;


import com.epam.task4.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    Review findBy_id(ObjectId id);
}
