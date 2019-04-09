package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ISessionService sessionService;

    public ProjectEndpoint(@NotNull final ISessionService sessionService, @NotNull final IProjectService projectService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @Override
    public void createProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "project") @NotNull final Project project
    ) {
        sessionService.checkSession(session);
        projectService.createEntity(id, project);
    }

    @Override
    public Project findProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(session);
        return projectService.findEntity(id, session.getUserId());
    }

    @Override
    public Collection<Project> findAllProject(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        return projectService.findAll(session.getUserId());
    }

    @Override
    public Collection<Project> findAllNameProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(session);
        return projectService.findAllName(findParameter, session.getUserId());
    }

    @Override
    public Collection<Project> findAllDescriptionProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(session);
        return projectService.findAllDescription(findParameter, session.getUserId());
    }

    @Override
    public void editProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "project") @NotNull Project project
    ) {
        sessionService.checkSession(session);
        projectService.editEntity(id, project, session.getUserId());
    }

    @Override
    public void removeProject(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(session);
        projectService.removeEntity(id, session.getUserId());
    }

    @Override
    public void clearProject(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.checkSession(session);
        projectService.clearEntity(session.getUserId());
    }
}