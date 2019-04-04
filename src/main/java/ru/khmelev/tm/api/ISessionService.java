package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

public interface ISessionService extends IService<Session> {
    void setSession(Session session);

    void removeSession(Session session);

    void checkSession(@NotNull Session session);
}
