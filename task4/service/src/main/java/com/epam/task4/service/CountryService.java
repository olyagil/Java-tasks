package com.epam.task4.service;

import com.epam.task4.entity.Country;
import org.bson.types.ObjectId;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findBy_id(ObjectId id);

    Country findByName(String name);

    void save(Country country);

    void delete(Country country);
}
