package com.epam.task4.service;

import com.epam.task4.entity.Tour;
import org.bson.types.ObjectId;

import java.util.List;

public interface TourService {
    List<Tour> findAll();

    Tour findBy_id(ObjectId id);

    void save(Tour tour);

    void delete(Tour tour);
}
