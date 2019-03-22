package ru.Hmelev.tm.service;

import com.google.common.hash.Hashing;
import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UserService {
    private byte[] password;
    private Bootstrap bootstrap;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, Bootstrap bootstrap) {
        this.userRepository = userRepository;
        this.bootstrap = bootstrap;
    }

    public void registry(String login, String pass, String roleStr) {
        if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty() && roleStr != null && !roleStr.isEmpty()) {
            String id = UUID.randomUUID().toString();
            password = Hashing.sha256().hashString(pass, UTF_8).asBytes();
            Role role = Role.valueOf(roleStr.toUpperCase());
            User user = new User(id, login, password, role);
            userRepository.persist(user);
        }
    }

    public boolean userLogin(String login, String pass) {
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(login)) {
                password = (Hashing.sha256().hashString(pass, UTF_8).asBytes());
                byte[] passwordUserRepository = user.getHashPassword();
                if (Arrays.equals(password, passwordUserRepository)) {
                    bootstrap.setIdUserSession(user.getId());
                    bootstrap.setUserRoleSession(user.getRole());
                    return true;
                } else return false;
            }
        }
        return false;
    }

    public Collection<User> userList() {
        return userRepository.findAll();
    }

    public void userSetPassword(String login, String pass) {
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(login)) {
                password = (Hashing.sha256().hashString(pass, UTF_8).asBytes());
                user.setHashPassword(password);
            }
        }
    }

    public User findUser(String id) {
        if (id != null && !id.isEmpty()) {
            return userRepository.findOne(id);
        }
        return null;
    }
}