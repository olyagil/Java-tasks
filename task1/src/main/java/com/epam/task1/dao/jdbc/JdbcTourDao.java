package com.epam.task1.dao.jdbc;

import com.epam.task1.dao.TourDao;
import com.epam.task1.entity.Country;
import com.epam.task1.entity.Hotel;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.entity.enumerution.TourType;
import com.epam.task1.exception.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tourDao")
public class JdbcTourDao implements TourDao {

    private static final String ID = "id";
    private static final String PHOTO = "photo";
    private static final String DATE = "tour_date";
    private static final String DURATION = "duration";
    private static final String DESCRIPTION = "description";
    private static final String COST = "cost";
    private static final String TOUR_TYPE = "tour_type";
    private static final String HOTEL_ID = "hotel_id";
    private static final String NAME = "country_name";
    private static final String COUNTRY_ID = "country_id";
    private static final String LOGIN = "login";

    private static final String SQL_SELECT_ALL = "select tour.id, photo, "
            + "tour_date, duration, description, cost, tour_type, hotel_id, "
            + "country_id, country_name from tour join country "
            + "on country.id=tour.country_id";

    private static final String SQL_SELECT_ALL_USER_BY_TOUR = "SELECT  "
            + "\"user\".id, login FROM tour join user_tour on tour.id = "
            + "user_tour.tour_id JOIN \"user\" ON \"user\".id = user_tour"
            + ".user_id where tour.id=?";
    private static final String SQL_INSERT_TOUR = "insert into tour (photo, "
            + "tour_date, duration, description, cost, tour_type, hotel_id, "
            + "country_id) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " where "
            + "tour.id=?";
    private static final String SQL_UPDATE_BY_ID = "update tour set photo=?, "
            + "tour_date=?, duration=?, description=?, cost=?,  tour_type=?, "
            + "hotel_id=?, country_id=? where tour.id=?";
    private static final String SQL_DELETE_TOUR_BY_ID = "delete from tour where"
            + " id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tour> read() throws DataBaseException {
        return jdbcTemplate.query(SQL_SELECT_ALL,
                (rs, rowNum) -> new Tour(rs.getLong(ID), rs.getString(PHOTO),
                        rs.getDate(DATE), rs.getDouble(DURATION),
                        rs.getString(DESCRIPTION), rs.getDouble(COST),
                        TourType.getById(rs.getInt(TOUR_TYPE)),
                        new Hotel(rs.getLong(HOTEL_ID)),
                        new Country(rs.getLong(COUNTRY_ID),
                                rs.getString(NAME))));
    }

    @Override
    public boolean create(Tour tour) throws DataBaseException {
        return jdbcTemplate.update(SQL_INSERT_TOUR, tour.getPhoto(),
                tour.getDate(), tour.getDuration(), tour.getDescription(),
                tour.getCost(),
                tour.getTourType().getId(), tour.getHotel().getId(),
                tour.getCountry().getId()) > 0;
    }

    @Override
    public Tour read(Long id) throws DataBaseException {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id},
                (rs, rowNum) -> new Tour(rs.getLong(ID), rs.getString(PHOTO),
                        rs.getDate(DATE), rs.getDouble(DURATION),
                        rs.getString(DESCRIPTION), rs.getDouble(COST),
                        TourType.getById(rs.getInt(TOUR_TYPE)),
                        new Hotel(rs.getLong(HOTEL_ID)),
                        new Country(rs.getLong(COUNTRY_ID),
                                rs.getString(NAME))));
    }

    @Override
    public boolean update(Tour tour) {
        return jdbcTemplate.update(SQL_UPDATE_BY_ID, tour.getPhoto(),
                tour.getDate(), tour.getDuration(), tour.getDescription(),
                tour.getCost(), tour.getTourType().getId(),
                tour.getHotel().getId(), tour.getCountry().getId(),
                tour.getId()) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(SQL_DELETE_TOUR_BY_ID, id) > 0;
    }

    @Override
    public List<User> readAllUsers(Tour tour) {
        return jdbcTemplate.query(SQL_SELECT_ALL_USER_BY_TOUR,
                new Object[]{tour.getId()},
                (rs, rowNum) -> new User(rs.getLong(ID), rs.getString(LOGIN)));
    }
}
