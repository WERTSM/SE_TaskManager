package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.bootstrap.ServiceLocator;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static ru.khmelev.tm.endpoint.util.SignatureUtil.sign;

@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.IUserEndpoint")
public final class UserEndpoint implements IUserEndpoint {

    @NotNull
    private ISessionService sessionService;

    @NotNull
    private IUserService userService;

    public UserEndpoint(@NotNull final ISessionService sessionService, @NotNull final IUserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Override
    public void createUser(
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "user") @NotNull final User user
    ) {
        userService.createEntity(id, user);
    }

    @Override
    public Collection<User> findAllUser(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        return userService.findAll();
    }

    @Override
    @NotNull
    public User findUser(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(session);
        return userService.findEntity(id);
    }

    @Override
    public void editUser(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "user") @NotNull User user
    ) {
        sessionService.checkSession(session);
        userService.editEntity(id, user);
    }

    @Override
    public void removeUser(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(session);
        userService.removeEntity(id);
    }

    @Override
    public void clearUser(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        userService.clearEntity();
    }

    @Override
    public Session userLogin(
            @WebParam(name = "login") @NotNull final String login,
            @WebParam(name = "password") @NotNull final String pass
    ) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (User user : userService.findAll()) {
                if (user.getLogin().equals(login)) {
                    String password = PasswordHashUtil.md5(pass);
                    String passwordUserRepository = user.getHashPassword();
                    if (passwordUserRepository.equals(password)) {
                        final Session session = new Session();
                        session.setId(UUID.randomUUID().toString());
                        session.setUserId(user.getId());
                        Random random = new Random();
                        session.setSignature(Objects.requireNonNull(sign(user, String.valueOf(random.nextInt(1000)), random.nextInt(1000))));
                        sessionService.setSession(session);
                        return session;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void userLogOut(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        sessionService.removeSession(session);
    }

    @Override
    public void userSetPassword(@WebParam(name = "session") @NotNull final Session session,
                                @WebParam(name = "login") @NotNull final String login,
                                @WebParam(name = "password") @NotNull final String pass
    ) {
        sessionService.checkSession(session);
        userService.userSetPassword(login, pass);
    }

    @Override
    public User getUserFromSession(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        return userService.getUserFromSession(session.getUserId());
    }

    @NotNull
    @Override
    public String getId(@WebParam(name = "user") @NotNull User user) {
        return user.getId();
    }

    @NotNull
    @Override
    public String getName(@WebParam(name = "user") @NotNull User user) {
        return user.getLogin();
    }
}