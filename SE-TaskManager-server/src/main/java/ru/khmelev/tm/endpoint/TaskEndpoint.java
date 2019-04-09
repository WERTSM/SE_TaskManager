package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;

import javax.jws.WebService;
import java.util.Collection;
import java.util.List;

@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.ITaskEndpoint")
public final class TaskEndpoint implements ITaskEndpoint {

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private final ISessionService sessionService;

    public TaskEndpoint(@NotNull final ISessionService sessionService, @NotNull final ITaskService taskService) {
        this.sessionService = sessionService;
        this.taskService = taskService;
    }

    @Override
    public void createTask(@NotNull final Session session, @NotNull final String id, @NotNull final Task task) {
        sessionService.checkSession(session);
        taskService.createEntity(id, task);
    }

    @Override
    public Task findTask(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        return taskService.findEntity(id, session.getUserId());
    }

    @Override
    public Collection<Task> findAllTAsk(@NotNull final Session session) {
        sessionService.checkSession(session);
        return taskService.findAll(session.getUserId());
    }

    @Override
    public Collection<Task> findAllNameTask(@NotNull final Session session, @NotNull String findParameter) {
        sessionService.checkSession(session);
        return taskService.findAllName(findParameter, session.getUserId());
    }

    @Override
    public Collection<Task> findAllDescriptionTask(@NotNull final Session session, @NotNull String findParameter) {
        sessionService.checkSession(session);
        return taskService.findAllDescription(findParameter, session.getUserId());
    }

    @Override
    public void editEntityTask(@NotNull final Session session, @NotNull final String id, @NotNull Task task) {
        sessionService.checkSession(session);
        taskService.editEntity(id, task, session.getUserId());
    }

    @Override
    public void removeTask(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        taskService.removeEntity(id, session.getUserId());
    }

    @Override
    public void clearTask(@NotNull final Session session) {
        sessionService.checkSession(session);
        taskService.clearEntity(session.getUserId());
    }

    @Override
    public List<Task> listTaskFromProject(@NotNull final Session session, @NotNull final String idProject) {
        sessionService.checkSession(session);
        return taskService.listTaskFromProject(idProject, session.getUserId());
    }

    @Override
    public void removeAllTaskFromProject(@NotNull final Session session, @NotNull final String idProject) {
        sessionService.checkSession(session);
        taskService.removeAllTaskFromProject(idProject, session.getUserId());
    }
}