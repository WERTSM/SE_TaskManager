package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityRepository;
import ru.khmelev.tm.api.IEntityService;
import ru.khmelev.tm.api.ISerializationRepository;
import ru.khmelev.tm.api.ISerializationService;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.IdentifiableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractEntityService<T extends Entity> extends AbstractSerializationService<T> implements IEntityService<T> {

    private ISerializationRepository<T> serializationRepository;

    AbstractEntityService(final ISerializationService<T> entityRepository) {
        super(entityRepository);
        this.entityRepository = entityRepository;
    }

    protected abstract TypeReference getTypeReference();

    @Override
    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        entityRepository.persist(id, entity);
    }

    @NotNull
    @Override
    public T findEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            return entityRepository.findOne(id, userId);
        }
        throw new ServiceException();
    }

    @NotNull
    @Override
    public Collection<T> findAll(@NotNull final String userId) {
        return entityRepository.findAll(userId);
    }

    @NotNull
    @Override
    public Collection<T> findAllName(String findParameter, String userId) {
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
    @Override
    public Collection<T> findAllDescription(String findParameter, String userId) {
        @NotNull final List<T> list = new ArrayList<>(entityRepository.findAll(userId));
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
            entityRepository.merge(id, entity, userId);
        }
    }

    @Override
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            entityRepository.remove(id, userId);
        }
    }

    @Override
    public void clearEntity(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            entityRepository.removeAll(userId);
        }
    }
}