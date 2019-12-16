package com.epam.task4.service.impl;

import com.epam.task4.entity.User;
import com.epam.task4.repository.UserRepository;
import com.epam.task4.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findBy_id(ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public boolean existsBy_id(ObjectId id) {
        return repository.existsBy_id(id);
    }

    @Override
    public void deleteBy_id(ObjectId id) {
        repository.deleteBy_id(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }
}
