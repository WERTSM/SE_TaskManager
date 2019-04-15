package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface IRepository<T extends Identifiable> {

    void persist(@NotNull String id, @NotNull final T entity) throws SQLException;

    @NotNull T findOne(@NotNull final String id) throws SQLException;

    @NotNull Collection<T> findAll();

    void merge(@NotNull final String id, @NotNull final T entity) throws SQLException;

    void remove(@NotNull final String id) throws SQLException;

    void removeAll() throws SQLException;

    void setConnection(@NotNull final Connection connection);
}