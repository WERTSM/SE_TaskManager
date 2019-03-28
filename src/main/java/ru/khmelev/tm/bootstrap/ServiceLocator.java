package ru.khmelev.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.api.ITaskService;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.IUserService;
import ru.khmelev.tm.entity.User;

public interface ServiceLocator {

    @NotNull IProjectService getProjectService();

    @NotNull ITaskService getTaskService();

    @NotNull IUserService getUserService();

    @NotNull ITerminalService getTerminalService();

    @Nullable User getUserSession();

    void setUserSession(final User userSession);
}