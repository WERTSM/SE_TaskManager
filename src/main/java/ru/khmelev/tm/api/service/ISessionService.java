package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

public interface ISessionService extends IService<Session> {

    void setSession(@NotNull final Session session);

    void removeSession(@NotNull final Session session);

    void checkSession(@NotNull final Session session);
}
