package ru.Hmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.EntityService;
import ru.Hmelev.tm.entity.Entity;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.exception.ServiceException;

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
    public T findEntity(@NotNull final String id, @NotNull final User user) {
        if (!id.isEmpty()) {
            userId = user.getId();
            return entityRepository.findOne(id, userId);
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<T> findAllEntities(@NotNull final User user) {
        userId = user.getId();
        return entityRepository.findAll(userId);
    }

    public void editEntity(@NotNull final String id, @NotNull T entity, @NotNull final User user) {
        if (!id.isEmpty() && userId != null) {
            userId = user.getId();
            entityRepository.merge(id, entity, userId);
        }
    }

    public void removeEntity(@NotNull final String id, @NotNull final User user) {
        if (!id.isEmpty() && userId != null && !userId.isEmpty()) {
            userId = user.getId();
            entityRepository.remove(id, userId);
        }
    }

    public void clearEntity(@NotNull final User user) {
        if (userId != null && !userId.isEmpty()) {
            userId = user.getId();
            entityRepository.removeAll(userId);
        }
    }
}