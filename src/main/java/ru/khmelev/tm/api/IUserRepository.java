package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserRepository extends IEntityRepository<User> {

    @NotNull User findOne(@NotNull final String id);

    @NotNull Collection<User> findAll();
}