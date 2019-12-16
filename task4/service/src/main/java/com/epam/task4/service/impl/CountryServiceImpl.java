package com.epam.task4.service.impl;

import com.epam.task4.entity.Country;
import com.epam.task4.repository.CountryRepository;
import com.epam.task4.service.CountryService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Country findBy_id(ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public Country findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void save(Country country) {
        repository.save(country);
    }

    @Override
    public void delete(Country country) {
        repository.delete(country);
    }
}
