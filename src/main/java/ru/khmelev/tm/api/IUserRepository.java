package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserRepository extends IRepository<User> {

    @NotNull Collection<User> findAll();

    @NotNull User findOne(@NotNull String id);
}