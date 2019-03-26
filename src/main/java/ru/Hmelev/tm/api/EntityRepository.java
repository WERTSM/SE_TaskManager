package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.Entity;

import java.util.Collection;

public interface EntityRepository<T extends Entity> {
    void persist(final String id, final T project);

    T findOne(final String id, final String userId);

    Collection<T> findAll(final String userId);

    void merge(final String id, final T entity, final String userId);

    void remove(final String id, final String userId);

    void removeAll(final String userId);
}