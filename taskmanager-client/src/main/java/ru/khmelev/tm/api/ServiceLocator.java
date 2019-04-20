package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import java.util.Map;

public interface ServiceLocator {

    @NotNull IProjectEndpoint getProjectEndpoint();

    @NotNull ITaskEndpoint getTaskEndpoint();

    @NotNull IUserEndpoint getUserEndpoint();

    @NotNull ITerminalService getTerminalService();

    @Nullable SessionDTO getSessionDTO();

    void setSessionDTO(@Nullable final SessionDTO sessionDTO);

    Map<String, Command> getCommandMap();
}