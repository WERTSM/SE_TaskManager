package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import java.util.Collection;

public interface ISerializationRepository<T extends Identifiable> {

    void persist(@NotNull final String id, @NotNull final T entity);

    @NotNull Collection<T> findAll(@NotNull final String userId);
}
