package ru.Hmelev.tm.bootstrap;

import ru.Hmelev.tm.api.InterfaceProjectService;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;

public interface ServiceLocator {
    BufferedReader getReader();

    InterfaceProjectService getProjectService();

    InterfaceTaskService getTaskService();

    UserService getUserService();

    User getUserSession();

    void setUserSession(User userSession);
}