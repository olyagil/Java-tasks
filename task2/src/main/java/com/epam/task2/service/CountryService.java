package com.epam.task2.service;

import com.epam.task2.entity.Country;

public interface CountryService extends DataService<Country> {

    Country read(String name);

}
