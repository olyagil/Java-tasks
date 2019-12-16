package com.epam.task2.dao.impl;

import com.epam.task2.annotation.LogExecutionTime;
import com.epam.task2.dao.CountryDao;
import com.epam.task2.entity.Country;
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
public class CriteriaCountryDao implements CountryDao {

    private static final String ID = "id";
    private static final String NAME = "name";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> read() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery =
                builder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Country read(Long id) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root).where(builder.equal(root.get(ID), id));
        TypedQuery<Country> query = entityManager.createQuery(criteriaQuery);
        Country country;
        try {
            country = query.getSingleResult();
        } catch (NoResultException e) {
            country = null;
        }
        return country;
    }

    @Override
    public long create(Country country) {
        entityManager.persist(country);
        return country.getId();
    }


    @Override
    public boolean update(Country country) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Country> criteriaUpdate =
                builder.createCriteriaUpdate(Country.class);
        Root<Country> root = criteriaUpdate.from(Country.class);
        criteriaUpdate.set(NAME, country.getName());
        criteriaUpdate.where(builder.equal(root.get(ID), country.getId()));

        return entityManager.createQuery(criteriaUpdate).executeUpdate() > 0;

    }

    @Override
    public boolean delete(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Country> criteriaDelete =
                builder.createCriteriaDelete(Country.class);
        Root<Country> root = criteriaDelete.from(Country.class);
        criteriaDelete.where(builder.equal(root.get(ID), id));
        return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;

    }

    @Override
    public Country read(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root).where(builder.equal(root.get(NAME), name));
        TypedQuery<Country> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
