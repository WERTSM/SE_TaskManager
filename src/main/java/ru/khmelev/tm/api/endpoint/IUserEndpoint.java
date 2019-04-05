package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint extends IEndpoint<User> {

    @WebMethod
    @Nullable Session userLogin(@NotNull final String login, @NotNull final String pass);

    @WebMethod
    void userLogOut(@NotNull final Session session);

    @WebMethod
    User getUserFromSession(@NotNull final Session session);

    @WebMethod
    void userSetPassword(@NotNull final Session session, @NotNull final String login, @NotNull final String password);

    @WebMethod
    @NotNull String getId(@NotNull final User user);

    @WebMethod
    @NotNull String getName(@NotNull final User user);
}