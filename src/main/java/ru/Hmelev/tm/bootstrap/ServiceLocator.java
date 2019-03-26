package ru.Hmelev.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.Hmelev.tm.api.InterfaceProjectService;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.service.TerminalService;
import ru.Hmelev.tm.service.UserService;

public interface ServiceLocator {

    @NotNull InterfaceProjectService getProjectService();

    @NotNull InterfaceTaskService getTaskService();

    @NotNull UserService getUserService();

    @NotNull TerminalService getTerminalService();

    @Nullable User getUserSession();

    void setUserSession(@Nullable final User userSession);
}