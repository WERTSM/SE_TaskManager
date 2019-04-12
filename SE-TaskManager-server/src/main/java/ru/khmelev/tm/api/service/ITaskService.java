package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;

public interface ITaskService extends IEntityService<Task> {

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId);

    Collection<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId);
}