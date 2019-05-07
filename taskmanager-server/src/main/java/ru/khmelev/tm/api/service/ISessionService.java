package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.exception.EndpointException;

public interface ISessionService extends IService<SessionDTO> {

    void setUser(@NotNull String id, @Nullable User user);

    void clearEntity();

    void checkSession(@NotNull final SessionDTO sessionDTO) throws EndpointException;
}
