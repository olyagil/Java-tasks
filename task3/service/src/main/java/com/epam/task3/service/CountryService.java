package com.epam.task3.service;

import com.epam.task3.entity.Country;

public interface CountryService extends DataService<Country> {

    Country read(String name);

}
