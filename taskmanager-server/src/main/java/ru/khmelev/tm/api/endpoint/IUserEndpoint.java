package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.exception.EndpointException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface IUserEndpoint {

    @WebMethod
    void createUser(@NotNull final String id, @NotNull final UserDTO userDTO);

    @WebMethod
    Collection<UserDTO> findAllUser(@NotNull final SessionDTO sessionDTO) throws EndpointException;

    @WebMethod
    UserDTO findUser(@NotNull final SessionDTO sessionDTO, @NotNull String id) throws EndpointException;

    @WebMethod
    void editUser(@NotNull final SessionDTO sessionDTO, @NotNull String id, UserDTO userDTO) throws EndpointException;

    @WebMethod
    void removeUser(@NotNull final SessionDTO sessionDTO, @NotNull String id) throws EndpointException;

    @WebMethod
    @Nullable SessionDTO userLogin(@NotNull final String login, @NotNull final String pass);

    @WebMethod
    void userLogOut(@NotNull final SessionDTO sessionDTO) throws EndpointException;

    @WebMethod
    UserDTO getUserFromSession(@NotNull final SessionDTO sessionDTO);

    @WebMethod
    void userSetPassword(@NotNull final SessionDTO sessionDTO, @NotNull final String login, @NotNull final String password) throws EndpointException;

    @WebMethod
    @NotNull String getId(@NotNull final UserDTO userDTO);

    @WebMethod
    @NotNull String getName(@NotNull final UserDTO userDTO);
}