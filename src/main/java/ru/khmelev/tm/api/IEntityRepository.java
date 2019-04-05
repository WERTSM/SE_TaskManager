package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;

public interface IEntityRepository<T extends Entity> extends ISerializationRepository<T> {

    @NotNull T findOne(@NotNull final String id, @NotNull final String userId);

    void merge(@NotNull final String id, @NotNull final T entity, @NotNull final String userId);

    void remove(@NotNull final String id, @NotNull final String userId);

    void removeAll(@NotNull final String userId);
}