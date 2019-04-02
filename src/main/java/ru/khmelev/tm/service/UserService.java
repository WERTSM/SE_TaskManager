package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.hash.Hashing;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IUserRepository;
import ru.khmelev.tm.api.IUserService;
import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.entity.User;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class UserService extends AbstractEntityService<User> implements IUserService {

    @NotNull
    final private Bootstrap bootstrap;

    @NotNull
    private IUserRepository userRepository;

    private String password;

    public UserService(@NotNull final IUserRepository userRepository, @NotNull final Bootstrap bootstrap) {
        super(userRepository);
        this.userRepository = userRepository;
        this.bootstrap = bootstrap;
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<User>>() {
        };
    }

    @Override
    public boolean userLogin(@NotNull final String login, @NotNull final String pass) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (User user : userRepository.findAll()) {
                if (user.getLogin().equals(login)) {
                    password = (Hashing.sha256().hashString(pass, UTF_8).toString());
                    String passwordUserRepository = user.getHashPassword();
                    if (passwordUserRepository.equals(password)) {
                        bootstrap.setUserSession(user);
                        return true;
                    } else return false;
                }
            }
        }
        return false;
    }

    @Override
    public void userSetPassword(@NotNull final String login, @NotNull final String pass) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (User user : userRepository.findAll()) {
                if (user.getLogin().equals(login)) {
                    password = (Hashing.sha256().hashString(pass, UTF_8).toString());
                    user.setHashPassword(password);
                }
            }
        }
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

    //In future...
    @Override
    public void editEntity(@NotNull String id, @NotNull User entity, @NotNull String userId) {
    }

    @Override
    public void removeEntity(@NotNull String id, @NotNull String userId) {
    }

    @Override
    public void clearEntity(@NotNull String userId) {
    }
}