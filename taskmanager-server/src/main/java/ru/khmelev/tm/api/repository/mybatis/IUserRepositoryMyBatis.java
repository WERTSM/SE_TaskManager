package ru.khmelev.tm.api.repository.mybatis;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface IUserRepositoryMyBatis {

    void persist(@NotNull String id, @NotNull final User user) throws SQLException;

    @NotNull User findOne(@NotNull final String id);

    @NotNull Collection<User> findAll();

    void merge(@NotNull final String id, @NotNull final User user);

    void remove(@NotNull final String id);

    void removeAll();
}