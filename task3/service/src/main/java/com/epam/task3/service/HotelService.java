package com.epam.task3.service;


import com.epam.task3.entity.Hotel;
import com.epam.task3.entity.User;

import java.util.List;

public interface HotelService extends DataService<Hotel> {
    long getCountRows();

    List<Hotel> readPaginatedList(int page, int maxResults);
}
