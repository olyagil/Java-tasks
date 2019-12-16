package com.epam.task4.repository;

import com.epam.task4.entity.Country;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {

    Country findByName(String name);

    Country findBy_id(ObjectId id);
}
