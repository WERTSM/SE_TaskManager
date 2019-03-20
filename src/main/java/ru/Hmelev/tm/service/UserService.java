package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.repository.UserRepository;

import java.util.UUID;

public class UserService {
    User user;
    Role role;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registry(String login, String password, String roleStr) {
        role = Role.valueOf(roleStr);
        user = new User(login, password, role);
        String idUser = UUID.randomUUID().toString();
        userRepository.persist(idUser, user);
    }
}