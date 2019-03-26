package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface InterfaceUserRepository {
    void persist(final User user);

    User findOne(final String id);

    Collection<User> findAll();
}