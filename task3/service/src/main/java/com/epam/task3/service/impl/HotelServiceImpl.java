package com.epam.task3.service.impl;

import com.epam.task3.dao.HotelDao;
import com.epam.task3.entity.Hotel;
import com.epam.task3.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@LogExecutionTime
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;


    @Override
    public List<Hotel> read() {
        return hotelDao.read();
    }

    @Override
    public long create(final Hotel hotel) {
        return hotelDao.create(hotel);
    }

    @Override
    public Hotel read(final Long id) {
        return hotelDao.read(id);
    }

    @Override
    public boolean update(final Hotel hotel) {
        return hotelDao.update(hotel);
    }

    @Override
    public boolean delete(final Long id) {
        return hotelDao.delete(id);
    }

    @Override
    public long getCountRows() {
        return hotelDao.getCountRows();
    }

    @Override
    public List<Hotel> readPaginatedList(int page, int maxResults) {
        return hotelDao.readPaginatedList(page,maxResults);
    }
}
