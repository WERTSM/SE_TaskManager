package ru.Hmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface InterfaceUserService {
    void registry(@NotNull final String login, @NotNull final String pass, @NotNull final String roleStr);

    boolean userLogin(@NotNull final String login, @NotNull final String pass);

    @NotNull Collection<User> userList();

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    @NotNull User findUser(@NotNull final String id);
}