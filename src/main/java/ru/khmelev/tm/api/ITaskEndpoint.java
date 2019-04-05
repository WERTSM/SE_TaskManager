package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.Task;

import java.util.List;

public interface ITaskEndpoint extends IEntityEndpoint<Task> {

    List<Task> listTaskFromProject(@NotNull final Session session, @NotNull final String idProject);

    void removeAllTaskFromProject(@NotNull final Session session, @NotNull final String idProject);

    void sort(@NotNull final Session session, @NotNull final List<Task> list, @NotNull final Sort sortParameter);
}