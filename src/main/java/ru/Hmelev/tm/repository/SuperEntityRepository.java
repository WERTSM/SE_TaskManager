package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.entity.Entity;

import java.util.*;

public final class SuperEntityRepository<T extends Entity> implements EntityRepository<T> {
    private final Map<String, T> mapEntity = new HashMap<>();

    public void persist(final String id, final T entity) {
        if (id != null && !id.isEmpty() && entity != null) {
            mapEntity.put(id, entity);
        }
    }

    public T findOne(final String id, final String userId) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            Collection<T> list = new ArrayList<>(findAll(userId));
            for (T entity : list) {
                if (entity.getId().equals(id)) {
                    return entity;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<T> findAll(final String userId) {
        if (userId != null && !userId.isEmpty()) {
            Collection<T> list = new ArrayList<>(mapEntity.values());
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                Entity entity = it.next();
                if (!entity.getUserId().equals(userId)) {
                    it.remove();
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public void merge(final String id, final T entity, final String userId) {
    }


    @Override
    public void remove(final String id, final String userId) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            Collection<T> list = mapEntity.values();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                T entity = it.next();
                if (entity.getId().equals(id) && entity.getUserId().equals(userId)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    @Override
    public void removeAll(final String userId) {
        if (userId != null && !userId.isEmpty()) {
            Collection<T> list = mapEntity.values();
            list.clear();
        }
    }
}