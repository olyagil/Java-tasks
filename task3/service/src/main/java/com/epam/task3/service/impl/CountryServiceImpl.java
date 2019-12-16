package com.epam.task3.service.impl;


import com.epam.task3.dao.CountryDao;
import com.epam.task3.entity.Country;
import com.epam.task3.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public List<Country> read() {
        return countryDao.read();
    }

    @Override
    public long create(final Country country) {
        return countryDao.create(country);
    }

    @Override
    public Country read(final Long id) {
        return countryDao.read(id);
    }

    @Override
    public boolean update(final Country country) {
        return countryDao.update(country);
    }

    @Override
    public boolean delete(final Long id) {
        return countryDao.delete(id);
    }

    @Override
    public Country read(String name) {
        return countryDao.read(name);
    }
}
