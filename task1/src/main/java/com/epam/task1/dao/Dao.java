package com.epam.task1.dao;

import com.epam.task1.entity.Entity;
import com.epam.task1.exception.DataBaseException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Dao<Type extends Entity> {

    List<Type> read() throws DataBaseException;

    boolean create(Type entity) throws DataBaseException;

    Type read(Long id) throws DataBaseException;

    boolean update(Type entity) throws DataBaseException;

    boolean delete(Long id) throws DataBaseException;


}
