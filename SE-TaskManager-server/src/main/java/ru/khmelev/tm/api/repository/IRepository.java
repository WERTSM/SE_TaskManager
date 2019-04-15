package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import java.util.Collection;

public interface IRepository<T extends Identifiable> {

    void persist(@NotNull String id, @NotNull final T entity);

    @NotNull T findOne(@NotNull final String id);

    @NotNull Collection<T> findAll();

    void merge(@NotNull final String id, @NotNull final T entity);

    void remove(@NotNull final String id);

    void removeAll();
}