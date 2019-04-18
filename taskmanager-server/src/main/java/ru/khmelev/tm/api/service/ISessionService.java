package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.dto.SessionDTO;

public interface ISessionService extends IService<SessionDTO> {

    void clearEntity();

    void setSession(@NotNull final SessionDTO sessionDTO);

    void removeSession(@NotNull final SessionDTO sessionDTO);

    void checkSession(@NotNull final SessionDTO sessionDTO);
}
