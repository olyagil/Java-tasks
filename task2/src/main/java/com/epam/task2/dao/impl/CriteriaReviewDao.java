package com.epam.task2.dao.impl;

import com.epam.task2.annotation.LogExecutionTime;
import com.epam.task2.dao.ReviewDao;
import com.epam.task2.entity.Review;
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
public class CriteriaReviewDao implements ReviewDao {

    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String TEXT = "text";
    private static final String USER = "user";
    private static final String TOUR = "tour";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Review> read() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery =
                builder.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Review read(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = builder.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        TypedQuery<Review> query = entityManager.createQuery(criteriaQuery);
        Review review;
        try {
            review = query.getSingleResult();
        } catch (NoResultException e) {
            review = null;
        }
        return review;
    }

    @Override
    public long create(Review review) {
        entityManager.persist(review);
        return review.getId();

    }


    @Override
    public boolean update(Review review) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Review> criteriaUpdate =
                builder.createCriteriaUpdate(Review.class);
        Root<Review> root = criteriaUpdate.from(Review.class);
        criteriaUpdate
                .set(DATE, review.getDate())
                .set(TEXT, review.getText())
                .set(USER, review.getUser())
                .set(TOUR, review.getTour());
        criteriaUpdate.where(builder.equal(root.get(ID), review.getId()));

        return entityManager.createQuery(criteriaUpdate).executeUpdate() > 0;
    }

    @Override
    public boolean delete(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Review> criteriaDelete =
                builder.createCriteriaDelete(Review.class);
        Root<Review> root = criteriaDelete.from(Review.class);
        criteriaDelete.where(builder.equal(root.get(ID), id));
        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
    }
}
