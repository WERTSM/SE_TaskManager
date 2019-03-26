package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.Entity;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface EntityService<T extends Entity> {
    void createEntity(final String id, final T entity);

    T findEntity(final String id, final User user);

    Collection<T> findAllEntities(final User user);

    void editEntity(final String id, final T entity, final User user);

    void removeEntity(final String id, final User user);

    void clearEntity(final User user);
}