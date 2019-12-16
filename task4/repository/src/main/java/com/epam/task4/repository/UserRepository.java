package com.epam.task4.repository;

import com.epam.task4.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findBy_id(ObjectId _id);

    Optional<User> findByLogin(String login);

    boolean existsBy_id(ObjectId _id);

    void deleteBy_id(ObjectId _id);

    boolean existsByLogin(String login);
}
