package ru.Hmelev.tm.service;

import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.EntityService;
import ru.Hmelev.tm.entity.Entity;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public abstract class AbstractEntityService<T extends Entity> implements EntityService<T> {
    final EntityRepository<T> entityRepository;
    protected String userId;

    AbstractEntityService(final EntityRepository<T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public void createEntity(final String id, final T entity) {
        entityRepository.persist(id, entity);
    }


    public T findEntity(final String id, final User user) {
        if (id != null && !id.isEmpty() && user != null) {
            userId = user.getId();
            return entityRepository.findOne(id, userId);
        }
        return null;
    }

    public Collection<T> findAllEntities(final User user) {
        if (user != null) {
            userId = user.getId();
            return entityRepository.findAll(userId);
        }
        return null;
    }

    public void editEntity(final String id, T entity, final User user) {
        if (id != null && !id.isEmpty() && userId != null && entity != null && user != null) {
            userId = user.getId();
            entityRepository.merge(id, entity, userId);
        }
    }

    public void removeEntity(final String id, final User user) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            userId = user.getId();
            entityRepository.remove(id, userId);
        }
    }

    public void clearEntity(final User user) {
        if (userId != null && !userId.isEmpty()) {
            userId = user.getId();
            entityRepository.removeAll(userId);
        }
    }
}