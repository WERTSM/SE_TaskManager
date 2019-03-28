package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityRepository;
import ru.khmelev.tm.api.IEntityService;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractEntityService<T extends Entity> implements IEntityService<T> {
    final IEntityRepository<T> IEntityRepository;
    protected String userId;

    AbstractEntityService(final IEntityRepository<T> IEntityRepository) {
        this.IEntityRepository = IEntityRepository;
    }

    @Override
    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        IEntityRepository.persist(id, entity);
    }

    @NotNull
    @Override
    public T findEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            return IEntityRepository.findOne(id, userId);
        }
        throw new ServiceException();
    }

    @NotNull
    @Override
    public Collection<T> findAll(@NotNull final String userId) {
        return IEntityRepository.findAll(userId);
    }

    @NotNull
    @Override
    public Collection<T> findAllName(String findParameter, String userId) {
        @NotNull final List<T> list = new ArrayList<>(IEntityRepository.findAll(userId));
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getName().contains(findParameter)) {
                iterator.remove();
            }
        }
        return list;
    }

    @NotNull
    @Override
    public Collection<T> findAllDescription(String findParameter, String userId) {
        @NotNull final List<T> list = new ArrayList<>(IEntityRepository.findAll(userId));
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().getDescription().contains(findParameter)) {
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public void editEntity(@NotNull final String id, @NotNull T entity, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            IEntityRepository.merge(id, entity, userId);
        }
    }

    @Override
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            IEntityRepository.remove(id, userId);
        }
    }

    @Override
    public void clearEntity(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            IEntityRepository.removeAll(userId);
        }
    }
}