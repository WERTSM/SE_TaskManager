package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.entity.Session;

import java.util.Collection;

public interface IEntityEndpoint<T extends Entity> {

    void createEntity(@NotNull final Session session, @NotNull String id, T entity);

    @NotNull T findEntity(@NotNull final Session session, @NotNull final String id, @NotNull final String userId);

    @NotNull Collection<T> findAll(@NotNull final Session session, @NotNull final String userId);

    @NotNull Collection<T> findAllName(@NotNull final Session session, String findParameter, String userId);

    @NotNull Collection<T> findAllDescription(@NotNull final Session session, String findParameter, String userId);

    void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull final T entity, @NotNull final String userId);

    void removeEntity(@NotNull final Session session, @NotNull final String id, @NotNull final String userId);

    void clearEntity(@NotNull final Session session, @NotNull final String userId);
}