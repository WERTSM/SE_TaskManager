package ru.khmelev.tm.serviceq;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IRepository;
import ru.khmelev.tm.entity.Identifiable;

import javax.sql.rowset.serial.SerialException;
import java.util.Collection;

public abstract class AbstractIdentifiableService<T extends Identifiable> extends AbstractSerializationService<T> {

    @NotNull
    private final IRepository<T> repository;

    AbstractIdentifiableService(@NotNull final IRepository<T> repository) {
        super(repository);
        this.repository = repository;
    }

    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        repository.persist(id, entity);
    }

    @NotNull
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @NotNull
    public T findEntity(@NotNull final String id) throws SerialException {
        if (!id.isEmpty()) {
            return repository.findOne(id);
        }
        throw new SerialException();
    }

    public void editEntity(@NotNull final String id, @NotNull final T entity) {
        if (!id.isEmpty()) {
            repository.merge(id, entity);
        }
    }

    public void removeEntity(@NotNull final String id) {
        if (!id.isEmpty()) {
            repository.remove(id);
        }
    }

    public void clearEntity() {
        repository.removeAll();
    }
}