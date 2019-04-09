package ru.khmelev.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;

import java.util.Map;

public interface ServiceLocator {

    @NotNull IProjectEndpoint getProjectEndpoint();

    @NotNull ITaskEndpoint getTaskEndpoint();

    @NotNull IUserEndpoint getUserEndpoint();

    @NotNull ITerminalService getTerminalService();

    @NotNull IAdminEndpoint getAdminEndpoint();

    @Nullable Session getSession();

    void setSession(@Nullable final Session session);

    Map<String, Command> getCommandMap();
}