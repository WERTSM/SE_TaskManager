package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.Entity;

import java.util.Collection;

public interface EntityRepository<T extends Entity> {
    void persist(String id, T project);

    T findOne(String id, String userId);

    Collection<T> findAll(String userId);

    void merge(String id, T entity, String userId);

    void remove(String id, String userId);

    void removeAll(String userId);
}