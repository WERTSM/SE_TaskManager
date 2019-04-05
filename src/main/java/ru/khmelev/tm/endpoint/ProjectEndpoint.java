package ru.khmelev.tm.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.exception.EndpointException;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.IProjectEndpoint")
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
    protected TypeReference getTypeReference() {
        throw new EndpointException();
    }

    @Override
    public void soQrt(@NotNull Session session, @NotNull List<Project> list, @NotNull Sort sortParameter) {

    }
}