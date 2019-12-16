package com.epam.task1.dao.jdbc;

import com.epam.task1.dao.HotelDao;
import com.epam.task1.entity.Hotel;
import com.epam.task1.entity.enumerution.Feature;
import com.epam.task1.exception.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hotelDao")
public class JdbcHotelDao implements HotelDao {

    private static final String ID = "id";
    private static final String NAME = "hotel_name";
    private static final String STARS = "stars";
    private static final String WEBSITE = "website";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String FEATURE = "features";

    private static final String SQL_SELECT_ALL = "select id, hotel_name, stars,"
            + " website, latitude, longitude, features from hotel";
    private static final String SQL_INSERT_HOTEL = "insert into hotel "
            + "(hotel_name, stars, website, latitude, longitude, features) "
            + "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_BY_ID = "select id, hotel_name, "
            + "stars, website, latitude, longitude, features from hotel where "
            + "id=?";
    private static final String SQL_UPDATE_BY_ID = "update hotel set "
            + "hotel_name=?, stars=?, website=?, latitude=?, longitude=?, "
            + "features=? where id=?";
    private static final String SQL_DELETE_BY_ID = "delete from hotel "
            + "where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Hotel> read() throws DataBaseException {
        return jdbcTemplate.query(SQL_SELECT_ALL,
                (rs, rowNum) -> new Hotel(rs.getLong(ID),
                        rs.getString(NAME), rs.getInt(STARS),
                        rs.getString(WEBSITE), rs.getString(LATITUDE),
                        rs.getString(LONGITUDE), Feature.getById(rs.getInt(
                        FEATURE))));
    }

    @Override
    public boolean create(Hotel hotel) throws DataBaseException {
        return jdbcTemplate.update(SQL_INSERT_HOTEL, hotel.getName(),
                hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(),
                hotel.getLongitude(), hotel.getFeature().getId()) > 0;
    }

    @Override
    public Hotel read(Long id) throws DataBaseException {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id},
                (rs, rowNum) -> new Hotel(rs.getLong(ID),
                        rs.getString(NAME), rs.getInt(STARS),
                        rs.getString(WEBSITE), rs.getString(LATITUDE),
                        rs.getString(LONGITUDE),
                        Feature.getById(rs.getInt(FEATURE))));
    }

    @Override
    public boolean update(Hotel hotel) {
        return jdbcTemplate.update(SQL_UPDATE_BY_ID,
                hotel.getName(), hotel.getStars(), hotel.getWebsite(),
                hotel.getLatitude(), hotel.getLongitude(),
                hotel.getFeature().getId(), hotel.getId()) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(SQL_DELETE_BY_ID, id) > 0;
    }
}
