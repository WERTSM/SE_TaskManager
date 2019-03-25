package ru.Hmelev.tm.service;

import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.EntityService;
import ru.Hmelev.tm.entity.Entity;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public abstract class AbstractEntityService<T extends Entity> implements EntityService<T> {
    protected String userId;
    EntityRepository<T> entityRepository;

    AbstractEntityService(EntityRepository<T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public void createEntity(String id, T entity) {
        entityRepository.persist(id, entity);
    }


    public T findEntity(String id, User user) {
        if (id != null && !id.isEmpty() && user != null) {
            userId = user.getId();
            return entityRepository.findOne(id, userId);
        }
        return null;
    }

    public Collection<T> findAllEntities(User user) {
        if (user != null) {
            userId = user.getId();
            return entityRepository.findAll(userId);
        }
        return null;
    }

    public void editEntity(String id, T entity, User user) {
        if (id != null && !id.isEmpty() && userId != null && entity != null && user != null) {
            userId = user.getId();
            entityRepository.merge(id, entity, userId);
        }
    }

    public void removeEntity(String id, User user) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            userId = user.getId();
            entityRepository.remove(id, userId);
        }
    }

    public void clearEntity(User user) {
        if (userId != null && !userId.isEmpty()) {
            userId = user.getId();
            entityRepository.removeAll(userId);
        }
    }
}