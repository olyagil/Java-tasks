package com.epam.task2.dao;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Dao<T> {

    List<T> read();

    long create(T entity);

    T read(Long id);

    boolean update(T entity);

    boolean delete(Long id);


}
