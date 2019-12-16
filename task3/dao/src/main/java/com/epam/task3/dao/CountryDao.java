package com.epam.task3.dao;

import com.epam.task3.entity.Country;

public interface CountryDao extends Dao<Country> {

    Country read(String name);

}
