package ru.khmelev.tm.service;

import com.google.common.hash.Hashing;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IUserService;
import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class UserService implements IUserService {
    final private Bootstrap bootstrap;
    final private UserRepository userRepository;
    private String password;

    public UserService(UserRepository userRepository, Bootstrap bootstrap) {
        this.userRepository = userRepository;
        this.bootstrap = bootstrap;
    }

    @Override
    public void registry(@NotNull final String login, @NotNull final String pass, @NotNull final String roleStr) {
        if (!login.isEmpty() && !pass.isEmpty() && !roleStr.isEmpty()) {
            String id = UUID.randomUUID().toString();
            password = Hashing.sha256().hashString(pass, UTF_8).toString();
            Role role = Role.valueOf(roleStr.toUpperCase());
            User user = new User(id, login, password, role);
            userRepository.persist(user);
        }
    }

    @NotNull
    @Override
    public User findUser(@NotNull final String id) {
        if (!id.isEmpty()) {
            return userRepository.findOne(id);
        }
        throw new ServiceException();
    }

    @NotNull
    @Override
    public Collection<User> userList() {
        return userRepository.findAll();
    }

    public boolean userLogin(@NotNull final String login, @NotNull final String pass) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (User user : userRepository.findAll()) {
                if (user.getName().equals(login)) {
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
                if (user.getName().equals(login)) {
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
        return user.getName();
    }
}