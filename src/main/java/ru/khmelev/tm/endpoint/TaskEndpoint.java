package ru.khmelev.tm.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.ISessionService;
import ru.khmelev.tm.api.ITaskEndpoint;
import ru.khmelev.tm.api.ITaskService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.exception.EndpointException;

import java.util.List;

public final class TaskEndpoint extends AbstractEntityEndpoint<Task> implements ITaskEndpoint {

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private final ISessionService sessionService;

    public TaskEndpoint(@NotNull final ISessionService sessionService, @NotNull final ITaskService taskService) {
        super(sessionService, taskService);
        this.sessionService = sessionService;
        this.taskService = taskService;
    }

    protected TypeReference getTypeReference() {
        throw new EndpointException();
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

    @Override
    public void sort(@NotNull final Session session, @NotNull List<Task> list, @NotNull Sort sortParameter) {
        sessionService.checkSession(session);
        taskService.sort(list, sortParameter);
    }
}