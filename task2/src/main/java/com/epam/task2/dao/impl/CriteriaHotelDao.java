package com.epam.task2.dao.impl;

import com.epam.task2.annotation.LogExecutionTime;
import com.epam.task2.dao.HotelDao;
import com.epam.task2.entity.Hotel;
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
public class CriteriaHotelDao implements HotelDao {

    private static final String ID = "id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> read() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = builder.createQuery(Hotel.class);
        Root<Hotel> root = criteriaQuery.from(Hotel.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Hotel read(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = builder.createQuery(Hotel.class);
        Root<Hotel> root = criteriaQuery.from(Hotel.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        TypedQuery<Hotel> typedQuery = entityManager.createQuery(criteriaQuery);
        Hotel hotel;
        try {
            hotel = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            hotel = null;
        }
        return hotel;
    }

    @Override
    public long create(Hotel hotel) {
        entityManager.persist(hotel);
        return hotel.getId();

    }


    @Override
    public boolean update(Hotel hotel) {
        entityManager.merge(hotel);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Hotel> criteriaDelete =
                builder.createCriteriaDelete(Hotel.class);
        Root<Hotel> root = criteriaDelete.from(Hotel.class);
        criteriaDelete.where(builder.equal(root.get(ID), id));
        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
    }
}
