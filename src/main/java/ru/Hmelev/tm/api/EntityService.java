package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.Entity;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface EntityService<T extends Entity> {
    void createEntity(String id, T entity);

    T findEntity(String id, User user);

    Collection<T> findAllEntities(User user);

    void editEntity(String id, T entity, User user);

    void removeEntity(String id, User user);

    void clearEntity(User user);
}