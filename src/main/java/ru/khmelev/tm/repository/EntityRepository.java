package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityRepository;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.RepositoryException;

import java.util.*;

public class EntityRepository<T extends Entity> implements IEntityRepository<T> {
    private final Map<String, T> mapEntity = new HashMap<>();

    public void persist(@NotNull final String id, @NotNull final T entity) {
        if (!id.isEmpty()) {
            mapEntity.put(id, entity);
        }
    }

    @NotNull
    public T findOne(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            Collection<T> list = new ArrayList<>(findAll(userId));
            for (T entity : list) {
                if (entity.getId().equals(id)) {
                    return entity;
                }
            }
        }
        throw new RepositoryException();
    }

    @NotNull
    @Override
    public Collection<T> findAll(@NotNull final String userId) {
        if (!userId.isEmpty()) {
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
        throw new RepositoryException();
    }

    @Override
    public void merge(@NotNull final String id, @NotNull final T entity, @NotNull final String userId) {
    }


    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
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
    public void removeAll(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            Collection<T> list = mapEntity.values();
            list.clear();
        }
    }
}