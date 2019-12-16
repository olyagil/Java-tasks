package com.epam.task2.dao.impl;

import com.epam.task2.annotation.LogExecutionTime;
import com.epam.task2.dao.UserDao;
import com.epam.task2.entity.Review;
import com.epam.task2.entity.Tour;
import com.epam.task2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
@Transactional
@Repository
@LogExecutionTime
public class CriteriaUserDao implements UserDao {

    private static final String LOGIN = "login";
    private static final String ID = "id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> read() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery =
                builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public User read(Long id) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot).where(builder.equal(userRoot.get(ID), id));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            log.error("The user with such id doesn't exist", e);
            return null;
        }
    }

    @Override
    public long create(User user) {
        entityManager.persist(user);
        return user.getId();
    }


    @Override
    public boolean update(User user) {
        entityManager.merge(user);
        return false;
    }

    @Override
    public boolean delete(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete =
                builder.createCriteriaDelete(User.class);
        Root<User> root = criteriaDelete.from(User.class);
        criteriaDelete.where(builder.equal(root.get(ID), id));
        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;

    }

    @Override
    public User read(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(builder.equal(root.get(LOGIN), login));
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    @Override
    public List<Tour> readAllTours(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = criteriaBuilder
                .createQuery(Tour.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get(ID),
                user.getId()));
        Join<User, Tour> answers = userRoot.join("tours");
        CriteriaQuery<Tour> cq = criteriaQuery.select(answers);
        TypedQuery<Tour> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Review> readAllReviews(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = criteriaBuilder
                .createQuery(Review.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get(ID),
                user.getId()));
        Join<User, Review> answers = userRoot.join("reviews");
        CriteriaQuery<Review> cq = criteriaQuery.select(answers);
        TypedQuery<Review> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
