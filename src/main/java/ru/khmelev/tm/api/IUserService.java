package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserService extends IService<User> {

    @NotNull Collection<User> findAll();

    boolean userLogin(@NotNull final String login, @NotNull final String pass);

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    @NotNull String getId(@NotNull final User user);

    @NotNull String getName(@NotNull final User user);
}