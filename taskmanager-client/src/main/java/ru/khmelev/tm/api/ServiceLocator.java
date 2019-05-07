package ru.khmelev.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import java.util.Map;

public interface ServiceLocator {

    @Nullable SessionDTO getSessionDTO();

    void setSessionDTO(@Nullable final SessionDTO sessionDTO);

    Map<String, Command> getCommandMap();
}