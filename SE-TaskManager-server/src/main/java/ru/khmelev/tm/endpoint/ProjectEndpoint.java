package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;

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
    public void createProject(@NotNull final Session session, @NotNull final String id, @NotNull final Project project) {
        sessionService.checkSession(session);
        projectService.createEntity(id, project);
    }

    @Override
    public Project findProject(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        return projectService.findEntity(id, session.getUserId());
    }

    @Override
    public Collection<Project> findAllProject(@NotNull final Session session) {
        sessionService.checkSession(session);
        return projectService.findAll(session.getUserId());
    }

    @Override
    public Collection<Project> findAllNameProject(@NotNull final Session session, @NotNull String findParameter) {
        sessionService.checkSession(session);
        return projectService.findAllName(findParameter, session.getUserId());
    }

    @Override
    public Collection<Project> findAllDescriptionProject(@NotNull final Session session, @NotNull String findParameter) {
        sessionService.checkSession(session);
        return projectService.findAllDescription(findParameter, session.getUserId());
    }

    @Override
    public void editProject(@NotNull final Session session, @NotNull final String id, @NotNull Project project) {
        sessionService.checkSession(session);
        projectService.editEntity(id, project, session.getUserId());
    }

    @Override
    public void removeProject(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        projectService.removeEntity(id, session.getUserId());
    }

    @Override
    public void clearProject(@NotNull final Session session) {
        sessionService.checkSession(session);
        projectService.clearEntity(session.getUserId());
    }
}