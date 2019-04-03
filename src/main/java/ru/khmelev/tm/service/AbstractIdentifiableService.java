package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IService;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.repository.IdentifiableRepository;

public abstract class AbstractIdentifiableService<T extends Identifiable> implements IService<T> {

    private IdentifiableRepository<T> identifiableRepository;

    public AbstractIdentifiableService(IdentifiableRepository<T> identifiableRepository) {
        this.identifiableRepository = identifiableRepository;
    }

    @Override
    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        identifiableRepository.persist(id, entity);
    }

    @NotNull
    public T findEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            return identifiableRepository.findOne(id, userId);
        }
        throw new identifiableRepository();
    }

    @NotNull
    @Override
    public Collection<T> findAll(@NotNull final String userId) {
        return entityRepository.findAll(userId);
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
