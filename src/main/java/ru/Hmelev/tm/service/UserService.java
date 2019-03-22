package ru.Hmelev.tm.service;

import com.google.common.hash.Hashing;
import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.repository.IUserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class UserService implements IUserService {
    private byte[] password;
    private Bootstrap bootstrap;
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository, Bootstrap bootstrap) {
        this.userRepository = userRepository;
        this.bootstrap = bootstrap;
    }

    public void registry(String login, String pass, String roleStr) {
        if (login != null && !login.isEmpty() && pass != null && !pass.isEmpty() && roleStr != null && !roleStr.isEmpty()) {
            String id = UUID.randomUUID().toString();
            password = Hashing.md5().hashString(pass, UTF_8).asBytes();
            Role role = Role.valueOf(roleStr.toUpperCase());
            User user = new User(id, login, password, role);
            userRepository.persist(user);
        }
    }

    public User findUser(String id) {
        if (id != null && !id.isEmpty()) {
            return userRepository.findOne(id);
        }
        return null;
    }

    public Collection<User> userList() {
        return userRepository.findAll();
    }

    public boolean userLogin(String login, String pass) {
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(login)) {
                password = (Hashing.md5().hashString(pass, UTF_8).asBytes());
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

    public void userSetPassword(String login, String pass) {
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(login)) {
                password = (Hashing.sha256().hashString(pass, UTF_8).asBytes());
                user.setHashPassword(password);
            }
        }
    }
}