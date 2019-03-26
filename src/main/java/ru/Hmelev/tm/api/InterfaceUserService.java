package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface InterfaceUserService {
    void registry(final String login, final String pass, final String roleStr);

    boolean userLogin(final String login, final String pass);

    Collection<User> userList();

    void userSetPassword(final String login, final String pass);

    User findUser(final String id);
}