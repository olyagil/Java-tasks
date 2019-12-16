package com.epam.task1.dao.jdbc;

import com.epam.task1.dao.UserDao;
import com.epam.task1.entity.Country;
import com.epam.task1.entity.Hotel;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.entity.enumerution.TourType;
import com.epam.task1.exception.DataBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class JdbcUserDao implements UserDao {
    private static final Logger logger =
            LoggerFactory.getLogger(JdbcUserDao.class);

    private static final String LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String ID = "id";
    private static final String PHOTO = "photo";
    private static final String DATE = "tour_date";
    private static final String DURATION = "duration";
    private static final String DESCRIPTION = "description";
    private static final String COST = "cost";
    private static final String TOUR_TYPE = "tour_type";
    private static final String HOTEL_ID = "hotel_id";
    private static final String COUNTRY_ID = "country_id";

    private static final String SQL_CREATE_USER = "insert into \"user\" "
            + "(login, password) values (?,?)";
    private static final String SQL_SELECT_USER_BY_ID = "select id, login, "
            + "password from \"user\" where id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "select id, login,"
            + " password from \"user\" where login=?";
    private static final String SQL_SELECT_ALL_USER = "select id, login, "
            + "password from \"user\"";
    private static final String SQL_SELECT_ALL_TOUR_BY_USER =
            "SELECT \"user\".id, login, tour.id, photo, tour_date, duration, "
                    + "description, cost, tour_type, hotel_id, country_id "
                    + "FROM \"user\" JOIN user_tour " +
                    "ON \"user\".id = user_tour.user_id JOIN tour " +
                    "ON tour.id = user_tour.tour_id where \"user\".id=?";
    private static final String SQL_UPDATE_USER_BY_ID = "update \"user\" set "
            + "login=?, password=? where id=?;";
    private static final String SQL_DELETE_USER_BY_ID = "delete from \"user\" "
            + "where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean create(User user) throws DataBaseException {
        try {
            jdbcTemplate.update(SQL_CREATE_USER, user.getLogin(),
                    user.getPassword());
            return true;
        } catch (DuplicateKeyException e) {
            logger.error("The user with rhis login is already exist.", e);
            throw new DataBaseException(e);
        }


    }

    @Override
    public User read(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID,
                new Object[]{id},
                (rs, rowNum) -> new User(rs.getLong(ID), rs.getString(LOGIN),
                        rs.getString(USER_PASSWORD)));
    }

    @Override
    public List<User> read() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USER,
                (rs, rowNum) -> new User(rs.getLong(ID),
                        rs.getString(LOGIN),
                        rs.getString(USER_PASSWORD)));
    }

    @Override
    public boolean update(User user) {
        return jdbcTemplate.update(SQL_UPDATE_USER_BY_ID, user.getLogin(),
                user.getPassword(),
                user.getId()) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(SQL_DELETE_USER_BY_ID, id) > 0;
    }

    @Override
    public User read(String login) throws DataBaseException {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_LOGIN,
                new Object[]{login},
                (rs, rowNum) -> new User(rs.getLong(ID), rs.getString(LOGIN),
                        rs.getString(USER_PASSWORD)));
    }

    @Override
    public List<Tour> readAllTours(User user) {
        return jdbcTemplate.query(SQL_SELECT_ALL_TOUR_BY_USER,
                new Object[]{user.getId()},
                (rs, rowNum) -> new Tour(rs.getLong(ID), rs.getString(PHOTO),
                        rs.getDate(DATE), rs.getDouble(DURATION),
                        rs.getString(DESCRIPTION), rs.getDouble(COST),
                        TourType.getById(rs.getInt(TOUR_TYPE)),
                        new Hotel(rs.getLong(HOTEL_ID)),
                        new Country(rs.getLong(COUNTRY_ID))));
    }
}
