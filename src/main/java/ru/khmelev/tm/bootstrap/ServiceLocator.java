package ru.khmelev.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.InterfaceProjectService;
import ru.khmelev.tm.api.InterfaceTaskService;
import ru.khmelev.tm.api.InterfaceTerminalService;
import ru.khmelev.tm.api.InterfaceUserService;
import ru.khmelev.tm.entity.User;

public interface ServiceLocator {

    @NotNull InterfaceProjectService getProjectService();

    @NotNull InterfaceTaskService getTaskService();

    @NotNull InterfaceUserService getUserService();

    @NotNull InterfaceTerminalService getTerminalService();

    @Nullable User getUserSession();

    void setUserSession(final User userSession);
}