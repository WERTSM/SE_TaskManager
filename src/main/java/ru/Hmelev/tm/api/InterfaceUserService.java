package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface InterfaceUserService {
    void registry(String login, String pass, String roleStr);

    boolean userLogin(String login, String pass);

    Collection<User> userList();

    void userSetPassword(String login, String pass);

    User findUser(String id);
}