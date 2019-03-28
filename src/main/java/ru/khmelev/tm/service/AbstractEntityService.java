package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityRepository;
import ru.khmelev.tm.api.IEntityService;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.ServiceException;

import java.util.Collection;

public abstract class AbstractEntityService<T extends Entity> implements IEntityService<T> {
    final IEntityRepository<T> IEntityRepository;
    protected String userId;

    AbstractEntityService(final IEntityRepository<T> IEntityRepository) {
        this.IEntityRepository = IEntityRepository;
    }

    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        IEntityRepository.persist(id, entity);
    }

    @NotNull
    public T findEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            return IEntityRepository.findOne(id, userId);
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAll(@NotNull final String userId) {
        return IEntityRepository.findAll(userId);
    }

    public void editEntity(@NotNull final String id, @NotNull T entity, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            IEntityRepository.merge(id, entity, userId);
        }
    }

    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            IEntityRepository.remove(id, userId);
        }
    }

    public void clearEntity(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            IEntityRepository.removeAll(userId);
        }
    }
}