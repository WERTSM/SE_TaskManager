package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.entity.dto.SessionDTO;
import ru.khmelev.tm.entity.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface IUserEndpoint {

    @WebMethod
    void createUser(@NotNull final String id, @NotNull final UserDTO userDTO);

    @WebMethod
    Collection<UserDTO> findAllUser(@NotNull final SessionDTO sessionDTO);

    @WebMethod
    UserDTO findUser(@NotNull final SessionDTO sessionDTO, @NotNull String id);

    @WebMethod
    void editUser(@NotNull final SessionDTO sessionDTO, @NotNull String id, UserDTO userDTO);

    @WebMethod
    void removeUser(@NotNull final SessionDTO sessionDTO, @NotNull String id);

    @WebMethod
    @Nullable SessionDTO userLogin(@NotNull final String login, @NotNull final String pass);

    @WebMethod
    void userLogOut(@NotNull final SessionDTO sessionDTO);

    @WebMethod
    UserDTO getUserFromSession(@NotNull final SessionDTO sessionDTO);

    @WebMethod
    void userSetPassword(@NotNull final SessionDTO sessionDTO, @NotNull final String login, @NotNull final String password);

    @WebMethod
    @NotNull String getId(@NotNull final UserDTO userDTO);

    @WebMethod
    @NotNull String getName(@NotNull final UserDTO userDTO);
}