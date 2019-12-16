package com.epam.task4.service.impl;

import com.epam.task4.entity.Hotel;
import com.epam.task4.repository.HotelRepository;
import com.epam.task4.repository.UserRepository;
import com.epam.task4.service.HotelService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;

    public HotelServiceImpl(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Hotel> findAll() {
        return repository.findAll();
    }

    @Override
    public Hotel findBy_id(ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public void save(Hotel hotel) {
        repository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        repository.delete(hotel);
    }
}
