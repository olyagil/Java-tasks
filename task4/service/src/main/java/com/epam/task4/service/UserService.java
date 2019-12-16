package com.epam.task4.service;

import com.epam.task4.entity.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findBy_id(ObjectId id);

    void save(User user);

    boolean existsBy_id(ObjectId id);

    void deleteBy_id(ObjectId id);

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);

}
