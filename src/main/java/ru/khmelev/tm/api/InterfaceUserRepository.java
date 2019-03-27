package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface InterfaceUserRepository {
    void persist(@NotNull final User user);

    User findOne(@NotNull final String id);

    @Nullable Collection<User> findAll();
}