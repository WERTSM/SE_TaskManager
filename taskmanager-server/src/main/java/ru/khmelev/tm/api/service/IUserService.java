package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.dto.UserDTO;

public interface IUserService extends IService<UserDTO> {

    @NotNull String getId(@NotNull final UserDTO user);

    @NotNull String getName(@NotNull final UserDTO user);

    void userSetPassword(@NotNull final String login, @NotNull final String pass);

    UserDTO getUserDTOFromSession(@NotNull final String userId);
}