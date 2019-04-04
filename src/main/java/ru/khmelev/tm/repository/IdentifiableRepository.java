package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IRepository;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.exception.RepositoryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IdentifiableRepository<T extends Identifiable> implements IRepository<T> {

    @NotNull
    private final Map<String, T> identifiable = new HashMap<>();

    public void persist(@NotNull final String id, @NotNull final T entity) {
        if (!id.isEmpty()) {
            identifiable.put(id, entity);
        }
    }

    @NotNull
    public T findOne(@NotNull final String id) {
        if (!id.isEmpty()) {
            for (T entity : identifiable.values()) {
                if (entity.getId().equals(id)) {
                    return entity;
                }
            }
        }
        throw new RepositoryException();
    }

    @NotNull
    public Collection<T> findAll() {
        return identifiable.values();
    }

    @NotNull
    public Collection<T> findAll(@NotNull String userId) {
        return findAll();
    }

    public void merge(@NotNull final String id, @NotNull final T entity) {
    }

    public void remove(@NotNull final String id) {
        if (!id.isEmpty()) {
            Collection<T> list = identifiable.values();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                T identifiable = it.next();
                if (identifiable.getId().equals(id)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public void removeAll() {
        identifiable.clear();
    }
}