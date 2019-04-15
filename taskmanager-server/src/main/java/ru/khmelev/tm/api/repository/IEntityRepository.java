package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface IEntityRepository<T extends Entity> {

    @NotNull T findOne(@NotNull final String id, @NotNull final String userId) throws SQLException;

    @NotNull Collection<T> findAll(@NotNull final String userId);

    void merge(@NotNull final String id, @NotNull final T entity, @NotNull final String userId) throws SQLException;

    void remove(@NotNull final String id, @NotNull final String userId) throws SQLException;

    void removeAll(@NotNull final String userId) throws SQLException;

    void persist(@NotNull final String id, @NotNull final T entity) throws SQLException;

    void setConnection(@NotNull final Connection connection);
}