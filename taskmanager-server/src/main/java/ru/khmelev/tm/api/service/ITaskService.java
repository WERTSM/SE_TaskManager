package ru.khmelev.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.dto.TaskDTO;

import java.util.Collection;

public interface ITaskService extends IEntityService<TaskDTO> {

    Collection<TaskDTO> listTaskFromProject(@NotNull final String projectId, @NotNull final String userId);
}