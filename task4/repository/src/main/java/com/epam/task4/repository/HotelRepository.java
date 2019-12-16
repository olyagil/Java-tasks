package com.epam.task4.repository;


import com.epam.task4.entity.Hotel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    Hotel findBy_id(ObjectId id);
}
