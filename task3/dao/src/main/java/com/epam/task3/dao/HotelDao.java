package com.epam.task3.dao;


import com.epam.task3.entity.Hotel;

import java.util.List;

public interface HotelDao extends Dao<Hotel> {
    long getCountRows();

    List<Hotel> readPaginatedList(int page, int maxResults);
}
