package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.bootstrap.ServiceLocator;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;

import javax.jws.WebService;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static ru.khmelev.tm.endpoint.util.SignatureUtil.sign;

@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.IUserEndpoint")
public final class UserEndpoint extends AbstractIdentifiableEndpoint<User> implements IUserEndpoint {

    @NotNull
    private ISessionService sessionService;

    @NotNull
    private IUserService userService;

    @NotNull
    private ServiceLocator serviceLocator;


    public UserEndpoint(@NotNull final ISessionService sessionService, @NotNull final IUserService userService, @NotNull final ServiceLocator serviceLocator) {
        super(sessionService, userService);
        this.sessionService = sessionService;
        this.userService = userService;
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Session userLogin(@NotNull final String login, @NotNull final String pass) {
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
                        serviceLocator.setSession(session);
                        return session;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void userLogOut(@NotNull final Session session) {
        sessionService.checkSession(session);
        sessionService.removeSession(session);
    }

    @Override
    public void userSetPassword(@NotNull final Session session, @NotNull final String login, @NotNull final String pass) {
        sessionService.checkSession(session);
        userService.userSetPassword(login, pass);
    }

    @Override
    public User getUserFromSession(@NotNull final Session session) {
        sessionService.checkSession(session);
        return userService.getUserFromSession(session.getUserId());
    }

    @NotNull
    @Override
    public String getId(@NotNull User user) {
        return user.getId();
    }

    @NotNull
    @Override
    public String getName(@NotNull User user) {
        return user.getLogin();
    }
}