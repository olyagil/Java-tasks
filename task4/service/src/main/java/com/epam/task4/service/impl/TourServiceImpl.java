package com.epam.task4.service.impl;

import com.epam.task4.entity.Tour;
import com.epam.task4.repository.TourRepository;
import com.epam.task4.service.TourService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository repository;

    public TourServiceImpl(TourRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tour> findAll() {
        return repository.findAll();
    }

    @Override
    public Tour findBy_id(ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public void save(Tour tour) {
        repository.save(tour);
    }

    @Override
    public void delete(Tour tour) {
        repository.delete(tour);
    }
}
