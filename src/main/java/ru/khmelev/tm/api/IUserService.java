package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

public interface IUserService extends IService<User> {

    @NotNull String getId(@NotNull final User user);

    @NotNull String getName(@NotNull final User user);

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    User getUserFromSession(@NotNull final String userId);
}