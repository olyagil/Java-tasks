package com.epam.task4.service;

import com.epam.task4.entity.Hotel;
import org.bson.types.ObjectId;

import java.util.List;

public interface HotelService {
    List<Hotel> findAll();

    Hotel findBy_id(ObjectId id);

    void save(Hotel hotel);

    void delete(Hotel by_id);
}
