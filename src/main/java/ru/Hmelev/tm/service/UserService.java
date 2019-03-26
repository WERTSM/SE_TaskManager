package ru.Hmelev.tm.service;

import com.google.common.hash.Hashing;
import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.api.InterfaceUserService;
import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.exception.ServiceException;
import ru.Hmelev.tm.repository.UserRepository;

import java.util.Collection;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class UserService implements InterfaceUserService {
    final private Bootstrap bootstrap;
    final private UserRepository userRepository;
    private String password;

    public UserService(UserRepository userRepository, Bootstrap bootstrap) {
        this.userRepository = userRepository;
        this.bootstrap = bootstrap;
    }

    public void registry(@NotNull final String login, @NotNull final String pass, @NotNull final String roleStr) {
        if (!login.isEmpty() && !pass.isEmpty() && !roleStr.isEmpty()) {
            String id = UUID.randomUUID().toString();
            password = Hashing.md5().hashString(pass, UTF_8).toString();
            Role role = Role.valueOf(roleStr.toUpperCase());
            User user = new User(id, login, password, role);
            userRepository.persist(user);
        }
    }

    @NotNull
    public User findUser(@NotNull final String id) {
        if (!id.isEmpty()) {
            return userRepository.findOne(id);
        }
        throw new ServiceException();
    }

    @NotNull
    public Collection<User> userList() {
        return userRepository.findAll();
    }

    public boolean userLogin(@NotNull final String login, @NotNull final String pass) {
        if (!login.isEmpty() && !pass.isEmpty()) {
            for (User user : userRepository.findAll()) {
                if (user.getName().equals(login)) {
                    password = (Hashing.md5().hashString(pass, UTF_8).toString());
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
}