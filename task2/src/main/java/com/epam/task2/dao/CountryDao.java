package com.epam.task2.dao;

import com.epam.task2.entity.Country;

public interface CountryDao extends Dao<Country> {

    Country read(String name);

}
