package ru.khmelev.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.ServiceException;
import ru.khmelev.tm.service.util.ConnectionJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public final class TaskService extends AbstractEntityService<Task> implements IEntityService<Task>, ITaskService {
    @NotNull
    private final ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    public void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            taskRepository.setConnection(connection);
            taskRepository.removeAllTaskFromProject(idProject, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Task> listTaskFromProject(@NotNull String idProject, @NotNull String userId) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            taskRepository.setConnection(connection);
            return taskRepository.listTaskFromProject(idProject, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ServiceException();
    }
}