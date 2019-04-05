package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;

public interface IUserEndpoint extends IEndpoint<User> {

    @Nullable Session userLogin(@NotNull final String login, @NotNull final String pass);

    void userLogOut(@NotNull final Session session);

    User getUserFromSession(@NotNull final Session session);

    void userSetPassword(@NotNull final Session session, @NotNull final String login, @NotNull final String password);

    @NotNull String getId(@NotNull final User user);

    @NotNull String getName(@NotNull final User user);
}