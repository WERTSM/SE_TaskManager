package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import java.util.Collection;

public interface IService<T extends Identifiable> {

    void createEntity(@NotNull final String id, @NotNull final T entity);

    @NotNull T findEntity(@NotNull final String id);

    @NotNull Collection<T> findAll();

    void editEntity(@NotNull final String id, @NotNull final T entity);

    void removeEntity(@NotNull final String id);
}