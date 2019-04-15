package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;
import java.util.List;

public final class TaskService extends AbstractEntityService<Task> implements IEntityService<Task>, ITaskService {
    @NotNull
    private final ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<Task>>() {
        };
    }

    public void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        taskRepository.removeAllTaskFromProject(idProject, userId);
    }

    @Override
    public Collection<Task> listTaskFromProject(@NotNull String idProject, @NotNull String userId) {
        return taskRepository.listTaskFromProject(idProject, userId);
    }
}