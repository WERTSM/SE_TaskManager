package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface InterfaceUserRepository {
    void persist(User user);

    User findOne(String id);

    Collection<User> findAll();
}