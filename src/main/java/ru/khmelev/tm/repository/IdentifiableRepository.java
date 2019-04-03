package ru.khmelev.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.exception.RepositoryException;

import java.util.*;

public class IdentifiableRepository<T extends Identifiable> {

    @NotNull
    private final Map<String, T> identifiable = new HashMap<>();

    public void persist(@NotNull final String id, @NotNull final T entity) {
        if (!id.isEmpty()) {
            identifiable.put(id, entity);
        }
    }

    @NotNull
    public T findOne(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            Collection<T> list = new ArrayList<>(findAll(userId));
            for (T entity : list) {
                if (entity.getId().equals(id)) {
                    return entity;
                }
            }
        }
        throw new RepositoryException();
    }

    @NotNull
    public Collection<T> findAll(@NotNull final String userId) {
        return identifiable.values();
    }

    public void merge(@NotNull final String id, @NotNull final T entity, @NotNull final String userId) {
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

    public void removeAll(@NotNull final String userId) {
        if (!userId.isEmpty()) {
            Collection<T> list = identifiable.values();
            list.clear();
        }
    }
}