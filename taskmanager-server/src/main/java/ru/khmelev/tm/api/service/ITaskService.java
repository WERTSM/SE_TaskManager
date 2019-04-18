package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.dto.TaskDTO;

import java.util.Collection;

public interface ITaskService extends IEntityService<TaskDTO> {

    void removeAllTaskFromProject(@NotNull final String projectId, @NotNull final String userId);

    Collection<TaskDTO> listTaskFromProject(@NotNull final String projectId, @NotNull final String userId);
}