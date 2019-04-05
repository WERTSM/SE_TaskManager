package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityRepository;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractEntityService<T extends Entity> extends AbstractSerializationService<T> {

    @NotNull
    private final IEntityRepository<T> entityRepository;

    AbstractEntityService(@NotNull final IEntityRepository<T> entityRepository) {
        super(entityRepository);
        this.entityRepository = entityRepository;
    }

    protected abstract TypeReference getTypeReference();

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

    @NotNull
    public Collection<T> findAllName(@NotNull final String findParameter, @NotNull final String userId) {
        @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getName().contains(findParameter)) {
                iterator.remove();
            }
        }
        return list;
    }

    @NotNull
    public Collection<T> findAllDescription(@NotNull final String findParameter, @NotNull final String userId) {
        @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getDescription().contains(findParameter)) {
                iterator.remove();
            }
        }
        return list;
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