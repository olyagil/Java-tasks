package com.epam.task3.dao.impl;


import com.epam.task3.dao.TourDao;
import com.epam.task3.dao.util.PredicateBuilder;
import com.epam.task3.dao.util.SearchParameter;
import com.epam.task3.entity.Review;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
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
//@LogExecutionTime
public class CriteriaTourDao implements TourDao {

    private static final String ID = "id";


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tour> read() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery =
                builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Tour> readPaginatedList(int pageNumber, int maxResults) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery =
                builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        criteriaQuery.orderBy(builder.asc(root.get(ID))).select(root);
        TypedQuery<Tour> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((pageNumber - 1) * maxResults);
        typedQuery.setMaxResults(maxResults);
        return typedQuery.getResultList();
    }

    @Override
    public long getCountRows() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(countQuery.from(Tour.class)));
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    @Override
    public Tour read(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        TypedQuery<Tour> query = entityManager.createQuery(criteriaQuery);
        Tour tour;
        try {
            tour = query.getSingleResult();
        } catch (NoResultException e) {
            tour = null;
        }
        return tour;
    }

    @Override
    public long create(Tour tour) {
        entityManager.persist(tour);
        return tour.getId();

    }


    @Override
    public boolean update(Tour tour) {
        entityManager.merge(tour);
        return true;

    }

    @Override
    public boolean delete(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Tour> criteriaDelete =
                builder.createCriteriaDelete(Tour.class);
        Root<Tour> root = criteriaDelete.from(Tour.class);
        criteriaDelete.where(builder.equal(root.get(ID), id));
        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;

    }

    @Override
    public List<User> readAllUsers(Tour tour) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<Tour> userRoot = criteriaQuery.from(Tour.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get(ID),
                tour.getId()));
        Join<Tour, User> users = userRoot.join("users");
        CriteriaQuery<User> cq = criteriaQuery.select(users);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Review> readAllReviews(Tour tour) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = builder.createQuery(Review.class);
        Root<Tour> reviews = criteriaQuery.from(Tour.class);
        criteriaQuery.where(builder.equal(reviews.get(ID), tour.getId()));
        Join<Tour, Review> join = reviews.join("reviews");
        CriteriaQuery<Review> query = criteriaQuery.select(join);
        TypedQuery<Review> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Tour> findByCriteria(List<SearchParameter> parameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> tours = criteriaQuery.from(Tour.class);
        List<Predicate> predicates = PredicateBuilder.buildPredicate(parameters,
                entityManager, tours);
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(criteriaQuery.select(tours)).getResultList();
    }


}
