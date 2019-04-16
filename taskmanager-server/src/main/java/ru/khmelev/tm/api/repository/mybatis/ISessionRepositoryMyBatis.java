package ru.khmelev.tm.api.repository.mybatis;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

import java.util.Collection;

public interface ISessionRepositoryMyBatis {

    void persist(@NotNull String id, @NotNull final Session session);

    @NotNull Session findOne(@NotNull final String id);

    @NotNull Collection<Session> findAll();

    void merge(@NotNull final String id, @NotNull final Session session);

    void remove(@NotNull final String id);

    void removeAll();
}