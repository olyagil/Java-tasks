package com.epam.task4.repository;

import com.epam.task4.entity.Tour;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TourRepository extends MongoRepository<Tour, String> {

    Tour findBy_id(ObjectId _id);

}
