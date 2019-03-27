package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;

import java.util.Collection;

public interface EntityRepository<T extends Entity> {
    void persist(@NotNull final String id, @NotNull final T project);

    @NotNull T findOne(@NotNull final String id, @NotNull final String userId);

    @NotNull Collection<T> findAll(@NotNull final String userId);

    void merge(@NotNull final String id, @NotNull final T entity, @NotNull final String userId);

    void remove(@NotNull final String id, @NotNull final String userId);

    void removeAll(@NotNull final String userId);
}