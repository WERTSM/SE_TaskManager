package ru.Hmelev.tm.bootstrap;

import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;

public interface ServiceLocator {
    BufferedReader getReader();

    ProjectService getProjectService();

    TaskService getTaskService();

    UserService getUserService();

    User getUserSession();

    void setUserSession(User userSession);
}