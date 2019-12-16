package com.epam.task1.service.impl;

import com.epam.task1.dao.HotelDao;
import com.epam.task1.entity.Hotel;
import com.epam.task1.exception.DataBaseException;
import com.epam.task1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hotelService")
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;


    @Override
    public List<Hotel> read() throws DataBaseException {
        return hotelDao.read();
    }

    @Override
    public boolean create(final Hotel hotel) throws DataBaseException {
        return hotelDao.create(hotel);
    }

    @Override
    public Hotel read(final Long id) throws DataBaseException {
        return hotelDao.read(id);
    }

    @Override
    public boolean update(final Hotel hotel) throws DataBaseException {
        return hotelDao.update(hotel);
    }

    @Override
    public boolean delete(final Long id) throws DataBaseException {
        return hotelDao.delete(id);
    }
}
