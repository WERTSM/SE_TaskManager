package ru.khmelev.tm.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISerializationService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Project;

import java.util.List;

public class ProjectService extends AbstractEntityService<Project> implements IEntityService<Project>, ISerializationService<Project>, IProjectService {

    @NotNull private IProjectRepository projectRepository;
    @NotNull private ITaskService taskService;

    public ProjectService(@NotNull final IProjectRepository projectRepository, @NotNull final ITaskService taskService) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    protected TypeReference getTypeReference() {
        return new TypeReference<List<Project>>() {
        };
    }

    @Override
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            taskService.removeAllTaskFromProject(id, userId);
            projectRepository.remove(id, userId);
        }
    }
}