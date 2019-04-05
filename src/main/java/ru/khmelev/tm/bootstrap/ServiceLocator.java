package ru.khmelev.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Session;

import java.util.Map;

public interface ServiceLocator {

    @NotNull IProjectEndpoint getProjectEndpoint();

    @NotNull ITaskEndpoint getTaskEndpoint();

    @NotNull IUserEndpoint getUserEndpoint();

    @NotNull ITerminalService getTerminalService();

    @Nullable Session getSession();

    void setSession(@Nullable final Session session);

    Map<String, Command> getCommandMap();
}