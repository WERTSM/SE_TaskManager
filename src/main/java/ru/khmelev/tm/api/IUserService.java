package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import java.util.Collection;

public interface IUserService {
    void registry(@NotNull final String login, @NotNull final String pass, @NotNull final String roleStr);

    boolean userLogin(@NotNull final String login, @NotNull final String pass);

    @NotNull Collection<User> userList();

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    @NotNull User findUser(@NotNull final String id);

    @NotNull String getId(@NotNull final User user);

    @NotNull String getName(@NotNull final User user);
}