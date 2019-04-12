package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

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
    public void createTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "task") @NotNull final Task task
    ) {
        sessionService.checkSession(session);
        taskService.createEntity(id, task);
    }

    @Override
    public Task findTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(session);
        return taskService.findEntity(id, session.getUserId());
    }

    @Override
    public Collection<Task> findAllTAsk(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        return taskService.findAll(session.getUserId());
    }

    @Override
    public Collection<Task> findAllNameTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(session);
        return taskService.findAllName(findParameter, session.getUserId());
    }

    @Override
    public Collection<Task> findAllDescriptionTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(session);
        return taskService.findAllDescription(findParameter, session.getUserId());
    }

    @Override
    public void editEntityTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "task") @NotNull Task task
    ) {
        sessionService.checkSession(session);
        taskService.editEntity(id, task, session.getUserId());
    }

    @Override
    public void removeTask(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(session);
        taskService.removeEntity(id, session.getUserId());
    }

    @Override
    public void clearTask(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        taskService.clearEntity(session.getUserId());
    }

    @Override
    public Collection<Task> listTaskFromProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "idProject") @NotNull final String idProject
    ) {
        sessionService.checkSession(session);
        return taskService.listTaskFromProject(idProject, session.getUserId());
    }

    @Override
    public void removeAllTaskFromProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "idProject") @NotNull final String idProject
    ) {
        sessionService.checkSession(session);
        taskService.removeAllTaskFromProject(idProject, session.getUserId());
    }
}