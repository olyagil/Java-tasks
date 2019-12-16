package com.epam.task3.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DataService<T> {

    List<T> read();

    long create(T entity);

    T read(Long id);

    boolean update(T entity);

    boolean delete(Long id);
}
