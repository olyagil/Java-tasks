package com.epam.task2.dao.criteria;

import com.epam.task2.config.AppConfig;
import com.epam.task2.dao.ReviewDao;
import com.epam.task2.entity.*;
import com.epam.task2.entity.enumerution.Feature;
import com.epam.task2.entity.enumerution.TourType;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Slf4j
@Transactional
public class CriteriaReviewDaoTest {

    @Autowired
    private ReviewDao reviewDao;

    private Review expected;

    @Before
    public void setUp() {
        User user = User
                .builder()
                .id(36L)
                .login("litora@sitamet.net")
                .password("7057")
                .build();
        Hotel hotel = Hotel
                .builder()
                .id(14L)
                .name("concept")
                .stars(2)
                .website("http://cbc.ca/sed/ante/vivamus.aspx")
                .latitude("25.556716")
                .longitude("110.016031")
                .features(Arrays.asList(Feature.WIFI,
                Feature.WORK_DESK,
                Feature.OUTLETS_GALORE,
                Feature.BUSINESS_CENTRE))
                .build();
        Country country = Country
                .builder()
                .id(11L)
                .name("Bosnia and Herzegovina")
                .build();
        Tour tour = Tour
                .builder()
                .id(396L)
                .photo("http://dummyimage.com/141x207.png/ff4444/ffffff")
                .date(Date.valueOf("2018-12-26"))
                .duration(37.0)
                .description("diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna")
                .cost(BigDecimal.valueOf(450908.3))
                .tourType(TourType.ROMANTIC)
                .hotel(hotel)
                .country(country)
                .build();
        expected = Review
                .builder()
                .id(1L)
                .date(Date.valueOf("2018-10-21"))
                .text("a pede posuere nonummy integer non velit donec diam "
                        + "neque vestibulum eget vulputate ut ultrices vel "
                        + "augue vestibulum ante ipsum")
                .user(user)
                .tour(tour)
                .build();
    }

    @After
    public void tearDown() {
        expected = null;
    }


    @Test
    public void testReadAllReviews() {
        int actual = reviewDao.read().size();
        int expected = 1000;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadReviewById() {
        Review actual = reviewDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testDeleteById() {
        boolean actual = reviewDao.delete(expected.getId());
        Assert.assertTrue(actual);

    }

    @Test
    public void testSaveReview() {
        Review expected = Review.builder().date(Date.valueOf("2018-10-21"))
                .text("test").user(User.builder().id(1L).build()).tour(Tour.builder().id(1L).build())
                .build();
        reviewDao.create(expected);
        Review actual = reviewDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testUpdateReview() {
        expected.setText("test");
        reviewDao.update(expected);
        Review actual = reviewDao.read(expected.getId());
        log.info("ACTUAL: " + actual);
        log.info("EXPECTED: " + expected);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testTextValidationConstraintException() {
        expected.setId(null);
        expected.setText("");
        reviewDao.create(expected);
    }
}