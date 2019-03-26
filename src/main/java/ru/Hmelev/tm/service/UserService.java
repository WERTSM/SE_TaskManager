package ru.Hmelev.tm.service;

import com.google.common.hash.Hashing;
import ru.Hmelev.tm.api.InterfaceUserService;
import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
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

    public void registry(final String login, final String pass, final String roleStr) {
        if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty() && roleStr != null && !roleStr.isEmpty()) {
            String id = UUID.randomUUID().toString();
            password = Hashing.md5().hashString(pass, UTF_8).toString();
            Role role = Role.valueOf(roleStr.toUpperCase());
            User user = new User(id, login, password, role);
            userRepository.persist(user);
        }
    }

    public User findUser(final String id) {
        if (id != null && !id.isEmpty()) {
            return userRepository.findOne(id);
        }
        return null;
    }

    public Collection<User> userList() {
        return userRepository.findAll();
    }

    public boolean userLogin(final String login, final String pass) {
        if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty()) {
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

    public void userSetPassword(final String login, final String pass) {
        if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty()) {
            for (User user : userRepository.findAll()) {
                if (user.getName().equals(login)) {
                    password = (Hashing.sha256().hashString(pass, UTF_8).toString());
                    user.setHashPassword(password);
                }
            }
        }
    }
}