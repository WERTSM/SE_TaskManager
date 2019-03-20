package ru.Hmelev.tm.service;

import com.google.common.hash.Hashing;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.repository.UserRepository;

import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UserService {
    User user;
    byte[] password;
    Role role;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registry(String login, String pass, String roleStr) {
        role = Role.valueOf(roleStr);
        password = Hashing.md5().hashString(pass, UTF_8).asBytes();
        user = new User(login, password, role);
        String idUser = UUID.randomUUID().toString();
        userRepository.persist(idUser, user);
    }

    public boolean userLogin(String login, String pass) {
        for (User user : userRepository.findAll()) {
            byte[] passwordUser;
            if (user.getName() == login) {
                passwordUser = user.getHashPassword();
                return (passwordUser.equals(Hashing.md5().hashString(pass, UTF_8).asBytes()));
            }
        }
        return false;
    }
}