package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;

import java.util.Collection;

public interface IEntityService<T extends Entity> {
    void createEntity(@NotNull final String id, @NotNull final T entity);

    @NotNull T findEntity(@NotNull final String id, @NotNull final String userId);

    @NotNull Collection<T> findAll(@NotNull final String userId);

    @NotNull Collection<T> findAllName(String findParameter, String userId);

    @NotNull Collection<T> findAllDescription(String findParameter, String userId);

    void editEntity(@NotNull final String id, @NotNull final T entity, @NotNull final String userId);

    void removeEntity(@NotNull final String id, @NotNull final String userId);

    void clearEntity(@NotNull final String userId);
}