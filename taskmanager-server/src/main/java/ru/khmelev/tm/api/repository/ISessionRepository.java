package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

import java.util.Collection;

public interface ISessionRepository {

    void persist(@NotNull final Session session);

    @NotNull Session findOne(@NotNull final String id);

    @NotNull Collection<Session> findAll();

    void merge(@NotNull final Session session);

    void remove(@NotNull final Session session);

    void removeAll();
}
