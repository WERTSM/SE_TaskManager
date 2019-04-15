package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.service.util.ConnectionJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class ProjectService extends AbstractEntityService<Project> implements IEntityService<Project>, IProjectService {

    @NotNull
    private IProjectRepository projectRepository;
    @NotNull
    private ITaskService taskService;

    public ProjectService(@NotNull final IProjectRepository projectRepository, @NotNull final ITaskService taskService) {
        super(projectRepository);
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    @Override
    public void removeEntity(@NotNull final String id, @NotNull final String userId) {
        if (!id.isEmpty() && !userId.isEmpty()) {
            taskService.removeAllTaskFromProject(id, userId);
            try (Connection connection = ConnectionJDBC.getConnection()) {
                projectRepository.setConnection(connection);
                projectRepository.remove(id, userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}