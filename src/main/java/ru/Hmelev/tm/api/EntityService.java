package ru.Hmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.entity.Entity;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface EntityService<T extends Entity> {
    void createEntity(@NotNull final String id, @NotNull final T entity);

    @NotNull T findEntity(@NotNull final String id, @NotNull final User user);

    @NotNull Collection<T> findAllEntities(@NotNull final User user);

    void editEntity(@NotNull final String id, @NotNull final T entity, @NotNull final User user);

    void removeEntity(@NotNull final String id, @NotNull final User user);

    void clearEntity(@NotNull final User user);
}