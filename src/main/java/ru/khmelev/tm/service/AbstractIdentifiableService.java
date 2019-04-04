package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IRepository;
import ru.khmelev.tm.api.ISerializationRepository;
import ru.khmelev.tm.api.IService;
import ru.khmelev.tm.entity.Identifiable;

import javax.sql.rowset.serial.SerialException;
import java.util.Collection;

public abstract class AbstractIdentifiableService<T extends Identifiable> implements IService<T> {

    private ISerializationRepository<T> serializationRepository;

    public AbstractIdentifiableService(ISerializationRepository<T> serializationRepository) {
        this.serializationRepository = serializationRepository;
    }

    @Override
    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        identifiableRepository.persist(id, entity);
    }

    @NotNull
    @Override
    public Collection<T> findAll() {
        return identifiableRepository.findAll();
    }

    @NotNull
    @Override
    public T findEntity(@NotNull final String id) throws SerialException {
        if (!id.isEmpty()) {
            return identifiableRepository.findOne(id);
        }
        throw new SerialException();
    }

    @Override
    public void editEntity(@NotNull final String id, @NotNull T entity) {
        if (!id.isEmpty()) {
            identifiableRepository.merge(id, entity);
        }
    }

    @Override
    public void removeEntity(@NotNull final String id) {
        if (!id.isEmpty()) {
            identifiableRepository.remove(id);
        }
    }

    @Override
    public void clearEntity() {
        identifiableRepository.removeAll();
    }
}