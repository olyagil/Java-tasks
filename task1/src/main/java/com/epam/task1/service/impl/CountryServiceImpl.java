package com.epam.task1.service.impl;

import com.epam.task1.dao.CountryDao;
import com.epam.task1.entity.Country;
import com.epam.task1.exception.DataBaseException;
import com.epam.task1.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
    private static final Logger logger =
            LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private CountryDao countryDao;

    @Override
    public List<Country> read() throws DataBaseException {
        return countryDao.read();
    }

    @Override
    public boolean create(final Country country) throws DataBaseException {
        if (countryDao.read(country.getName()) == null) {
            return countryDao.create(country);
        } else {
            logger.error("Can't insert country to database");
            throw new DataBaseException();
        }
    }

    @Override
    public Country read(final Long id) throws DataBaseException {
        return countryDao.read(id);
    }

    @Override
    public boolean update(final Country country) throws DataBaseException {
        return countryDao.update(country);
    }

    @Override
    public boolean delete(final Long id) throws DataBaseException {
        return countryDao.delete(id);
    }
}
