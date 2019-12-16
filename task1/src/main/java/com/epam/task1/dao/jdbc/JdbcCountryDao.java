package com.epam.task1.dao.jdbc;

import com.epam.task1.dao.CountryDao;
import com.epam.task1.entity.Country;
import com.epam.task1.exception.DataBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryDao")
public class JdbcCountryDao implements CountryDao {
    private static final Logger logger =
            LoggerFactory.getLogger(JdbcCountryDao.class);

    private static final String NAME = "country_name";
    private static final String ID = "id";

    private static final String SQL_INSERT_COUNTRY = "insert into public"
            + ".country (country_name) values (?)";
    private static final String SQL_SELECT_ALL_COUNTRY = "select * from "
            +
            "public.country";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL_COUNTRY
            + " where id=?";
    private static final String SQL_SELECT_BY_NAME = SQL_SELECT_ALL_COUNTRY
            + " where country_name=?";
    private static final String SQL_UPDATE_BY_ID = "update country set "
            + "country_name=? where id=?";
    private static final String SQL_DELETE_BY_ID = "delete from country "
            + "where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> read() throws DataBaseException {
        List<Country> countries;
        try {
            countries = jdbcTemplate.query(SQL_SELECT_ALL_COUNTRY,
                    (rs, rowNum) -> new Country(rs.getLong(ID),
                            rs.getString(NAME)));

        } catch (Exception e) {
            logger.error("Can't get all countries from db", e);
            throw new DataBaseException(e);
        }
        return countries;
    }

    @Override
    public boolean create(Country country) throws DataBaseException {
        try {
            jdbcTemplate.update(SQL_INSERT_COUNTRY, country.getName());
            return true;
        } catch (DuplicateKeyException e) {
            logger.error("The country is already exist");
            throw new DataBaseException(e);
        }
    }

    @Override
    public Country read(Long id) throws DataBaseException {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id},
                (rs, rowNum) -> new Country(rs.getLong(ID), rs.getString(NAME)));
    }

    @Override
    public boolean update(Country country) throws DataBaseException {
        return jdbcTemplate.update(SQL_UPDATE_BY_ID, country.getName(),
                country.getId()) > 1;
    }

    @Override
    public boolean delete(Long id) throws DataBaseException {
        return jdbcTemplate.update(SQL_DELETE_BY_ID, id) > 0;
    }

    @Override
    public Country read(String name) throws DataBaseException {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME,
                new Object[]{name},
                (rs, rowNum) -> new Country(rs.getLong(ID), rs.getString(NAME)));
    }
}
