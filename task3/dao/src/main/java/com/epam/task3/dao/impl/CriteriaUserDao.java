package com.epam.task3.dao.impl;

import com.epam.task3.dao.UserDao;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
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
//@LogExecutionTime
public class CriteriaUserDao implements UserDao {

    private static final String LOGIN = "login";
    private static final String ID = "id";
    private static final String PASSWORD = "password";
    private static final String DATE = "date";
    private static final String TOUR = "tour";
    private static final String REVIEWS = "reviews";
    private static final String TOURS = "tours";

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
    public List<User> readPaginatedList(int pageNumber, int maxResults) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery =
                builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.orderBy(builder.asc(root.get(ID))).select(root);
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((pageNumber - 1) * maxResults);
        typedQuery.setMaxResults(maxResults);
        return typedQuery.getResultList();
    }

    @Override
    public long getCountRows() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(countQuery.from(User.class)));
        return entityManager.createQuery(countQuery).getSingleResult();
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
    public User read(String login, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate loginRestriction = builder.and(
                builder.equal(root.get(LOGIN), login),
                builder.equal(root.get(PASSWORD), password)
        );
        criteriaQuery.select(root).where(loginRestriction);
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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder
                .createQuery(Tour.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(userRoot.get(ID),
                user.getId()));
        Join<User, Tour> answers = userRoot.join(TOURS);
        CriteriaQuery<Tour> cq = criteriaQuery.select(answers);
        criteriaQuery.orderBy(builder.desc(answers.get(DATE))).select(answers);
        TypedQuery<Tour> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Review> readAllReviews(User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = builder
                .createQuery(Review.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(userRoot.get(ID),
                user.getId()));
        Join<User, Review> answers = userRoot.join(REVIEWS);
        answers.fetch(TOUR);
        CriteriaQuery<Review> cq = criteriaQuery.select(answers);
        criteriaQuery.orderBy(builder.desc(answers.get(DATE))).select(answers);

        TypedQuery<Review> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
