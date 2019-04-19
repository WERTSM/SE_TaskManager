package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.entity.dto.UserDTO;

public interface IUserService extends IService<UserDTO> {

    @NotNull String getId(@NotNull final UserDTO user);

    @NotNull String getName(@NotNull final UserDTO user);

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    UserDTO getUserFromSession(@NotNull final String userId);
}