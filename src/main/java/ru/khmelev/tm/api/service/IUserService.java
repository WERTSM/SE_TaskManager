package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;

import javax.jws.WebService;

public interface IUserService extends IService<User> {

    @NotNull String getId(@NotNull final User user);

    @NotNull String getName(@NotNull final User user);

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    User getUserFromSession(@NotNull final String userId);
}