package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

public interface IUserRepository extends IRepository<User> {
    void persist(@NotNull String id, @NotNull User user);
}