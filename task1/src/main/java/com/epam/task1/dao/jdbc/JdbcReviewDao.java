package com.epam.task1.dao.jdbc;

import com.epam.task1.dao.ReviewDao;
import com.epam.task1.entity.Review;
import com.epam.task1.entity.Tour;
import com.epam.task1.entity.User;
import com.epam.task1.exception.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reviewDao")
public class JdbcReviewDao implements ReviewDao {

    private static final String TOUR_ID = "tour_id";
    private static final String ID = "id";
    private static final String DATE = "review_date";
    private static final String TEXT = "review_text";
    private static final String USER_ID = "user_id";
    private static final String SQL_SELECT_ALL = "select id, review_date, "
            + "review_text, user_id, tour_id from review";
    private static final String SQL_INSERT_REVIEW = "insert into review "
            + "(review_date, review_text, user_id, tour_id) values (?,?,?,?)";
    private static final String SQL_SELECT_BY_ID = "select id, review_date, "
            + "review_text, user_id, tour_id from review where id=?";
    private static final String SQL_UPDATE_REVIEW = "update review set "
            + "review_date=?, review_text=?, user_id=?, tour_id=? "
            + "where id=?";
    private static final String DELETE_BY_ID = "delete from review where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Review> read() throws DataBaseException {
        return jdbcTemplate.query(SQL_SELECT_ALL,
                (rs, rowNum) -> new Review(rs.getLong(ID),
                        rs.getDate(DATE), rs.getString(TEXT),
                        new User(rs.getLong(USER_ID)),
                        new Tour(rs.getLong(TOUR_ID))));
    }

    @Override
    public boolean create(Review review) throws DataBaseException {
        return jdbcTemplate.update(SQL_INSERT_REVIEW, review.getDate(),
                review.getText(), review.getUser().getId(),
                review.getTour().getId()) > 0;
    }

    @Override
    public Review read(Long id) throws DataBaseException {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id},
                (rs, rowNum) -> new Review(rs.getLong(ID), rs.getDate(DATE),
                        rs.getString(TEXT), new User(rs.getLong(USER_ID)),
                        new Tour(rs.getLong(TOUR_ID))));
    }

    @Override
    public boolean update(Review review) {
        return jdbcTemplate.update(SQL_UPDATE_REVIEW, review.getDate(),
                review.getText(), review.getUser().getId(),
                review.getTour().getId(), review.getId()) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(DELETE_BY_ID, id) > 0;
    }
}
