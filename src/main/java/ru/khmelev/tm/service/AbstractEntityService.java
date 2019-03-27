package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.EntityRepository;
import ru.khmelev.tm.api.EntityService;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.ServiceException;

import java.util.Collection;

public abstract class AbstractEntityService<T extends Entity> implements EntityService<T> {
    final EntityRepository<T> entityRepository;
    protected String userId;

    AbstractEntityService(final EntityRepository<T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        entityRepository.persist(id, entity);
    }

    @NotNull
    public T findEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            return entityRepository.findOne(id, userId);
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAll(@NotNull final String userId) {
        return entityRepository.findAll(userId);
    }

    public void editEntity(@NotNull final String id, @NotNull T entity, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            entityRepository.merge(id, entity, userId);
        }
    }

    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            entityRepository.remove(id, userId);
        }
    }

    public void clearEntity(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            entityRepository.removeAll(userId);
        }
    }
}