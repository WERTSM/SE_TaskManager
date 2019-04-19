package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserRepository {
    void persist(@NotNull User user);

    @NotNull User findOne(@NotNull String id);

    @NotNull Collection<User> findAll();

    void merge(@NotNull User user);

    void remove(@NotNull User user);

    void removeAll(@NotNull String userId);
}
