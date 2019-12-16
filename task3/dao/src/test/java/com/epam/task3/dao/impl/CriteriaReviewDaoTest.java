package com.epam.task3.dao.impl;

import com.epam.task3.config.AppConfig;
import com.epam.task3.dao.ReviewDao;
import com.epam.task3.entity.*;
import com.epam.task3.entity.enumerution.Feature;
import com.epam.task3.entity.enumerution.Roles;
import com.epam.task3.entity.enumerution.TourType;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashSet;

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
                .login("clacrouxz@facebook.com")
                .password("$2a$10$qIo1aIA1YXL8tlyzD6ladOUnlWB/GwxcYH9dnFWX1c0CkpfUzGWtO")
                .role(Roles.USER)
                .build();
        Hotel hotel = Hotel
                .builder()
                .id(87L)
                .name("contextually-based")
                .stars(3)
                .website("https://cbslocal.com/nisl/nunc/rhoncus/dui/vel.aspx")
                .latitude("39.8926924")
                .longitude("-8.3687908")
                .features(new HashSet<>(Arrays.asList(Feature.WIFI,
                        Feature.WORK_DESK,
                        Feature.OUTLETS_GALORE,
                        Feature.BUSINESS_CENTRE)))
                .build();
        Country country = Country
                .builder()
                .id(6L)
                .name("Luxembourg")
                .build();
        Tour tour = Tour
                .builder()
                .id(396L)
                .photo("iVBORw0KGgoAAAANSUhEUgAAABAAAAAQ"
                        + "CAYAAAAf8/9hAAAABGdBTUEAAK/INwWK6QAAABl0RVh0U29mdH"
                        + "dhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAH5SURBVDjLpZK/a"
                        + "5NhEMe/748kRqypmqQQgz/oUPUPECpCoEVwyNStIA6COFR33bo"
                        + "Ijg6mg4uL0k0EO1RFISKImkHQxlbQRAsx0dgKJm/e53nunnOw"
                        + "ViR5leJnuZs+973jHBHB/+D/ah7X2LXWloilyMw5YgtD3CDiB"
                        + "WN4Zno8bQcJHBFBucauZfsolZDCru0OfFcAAUISrLZDfPzSKxu"
                        + "iibOT+T6JCwDMtrQzYQvZHQ5Cw2h3GK0OI9AWBzJJZFOxgtJUGp"
                        + "TABQAiLu5OOviuGIEWkBUwC7pasNZj7N2ThNJUjBQY4pznAoEWsB"
                        + "WwxU+JFXSVRTzmQWvKRR5RG4KVGMgKrAVYflexAAugDCEygdbUCI"
                        + "2F7zobk7FZY76DIDQgrT9HCwwt1FsBhhIu4p4D3kiS8B0MJz28f"
                        + "tfGSPfl8MPLxbGBAqVpptbslJc+fEPMA7JDPrIpH3FX8LzaROdr"
                        + "E5O51jalgid3Lh4b6/sDALh6971riErGcFET58gwDPGndG9JT6R"
                        + "eHcwfPorGygu8rdxvGxMeP3XtzcofgigWZ0/EtQ7n0/sOTe0/Mo"
                        + "7V5WeoVu61z1yvZzZX+BsnZx9opYLpevXp7eXKIrL5UWit0n0r/"
                        + "Isb50bjRGreiyWmgs76lfM31y5tSQAAc6czHjONXLi13thygih+"
                        + "AEq4N6GqMsuhAAAAAElFTkSuQmCC")
                .date(Date.valueOf("2017-05-02"))
                .duration(18.0)
                .description("in tempus sit amet sem fusce consequat nulla " +
                        "nisl nunc nisl duis bibendum felis")
                .cost(BigDecimal.valueOf(150340.1))
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

//    @Test(expected = ConstraintViolationException.class)
//    public void testTextValidationConstraintException() {
//        expected.setId(null);
//        expected.setText("");
//        reviewDao.create(expected);
//    }
}