package com.epam.task1.dao;

import com.epam.task1.entity.Country;
import com.epam.task1.exception.DataBaseException;

public interface CountryDao extends Dao<Country> {

    Country read(String name) throws DataBaseException;

}
