package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.Task;

import java.util.List;

public interface ITaskService extends IEntityService<Task> {

    @NotNull List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId);

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId);
}