package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;

import java.util.List;

public interface IProjectEndpoint extends IEntityEndpoint<Project> {

    void sort(@NotNull final Session session, @NotNull final List<Project> list, @NotNull final Sort sortParameter);
}