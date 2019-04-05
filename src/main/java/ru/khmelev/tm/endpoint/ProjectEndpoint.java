package ru.khmelev.tm.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IProjectEndpoint;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.api.ISessionService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.exception.EndpointException;

import java.util.List;

public class ProjectEndpoint extends AbstractEntityEndpoint<Project> implements IProjectEndpoint {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ISessionService sessionService;

    public ProjectEndpoint(@NotNull final ISessionService sessionService, @NotNull final IProjectService projectService) {
        super(sessionService, projectService);
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @Override
    public void sort(@NotNull final Session session, @NotNull List<Project> list, @NotNull Sort sortParameter) {
        sessionService.checkSession(session);
        projectService.sort(sortParameter, list);
    }

    @Override
    protected TypeReference getTypeReference() {
        throw new EndpointException();
    }
}