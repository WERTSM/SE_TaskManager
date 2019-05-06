package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.dto.SessionDTO;
import ru.khmelev.tm.exception.EndpointException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@Singleton
@WebService(endpointInterface = "ru.khmelev.tm.api.endpoint.IProjectEndpoint")
public class ProjectEndpoint implements IProjectEndpoint {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ISessionService sessionService;

    @Inject
    public ProjectEndpoint(@NotNull final ISessionService sessionService, @NotNull final IProjectService projectService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @Override
    public void createProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "project") @NotNull final ProjectDTO projectDTO
    ) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        projectService.createEntity(id, projectDTO);
    }

    @Override
    public ProjectDTO findProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        return projectService.findEntity(id, sessionDTO.getUserId());
    }

    @Override
    public Collection<ProjectDTO> findAllProject(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        return projectService.findAll(sessionDTO.getUserId());
    }

    @Override
    public Collection<ProjectDTO> findAllNameProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        return projectService.findAllName(findParameter, sessionDTO.getUserId());
    }

    @Override
    public Collection<ProjectDTO> findAllDescriptionProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        return projectService.findAllDescription(findParameter, sessionDTO.getUserId());
    }

    @Override
    public void editProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "project") @NotNull ProjectDTO projectDTO
    ) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        projectService.editEntity(id, projectDTO, sessionDTO.getUserId());
    }

    @Override
    public void removeProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        projectService.removeEntity(id, sessionDTO.getUserId());
    }

    @Override
    public void clearProject(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        if (!sessionService.checkSession(sessionDTO))
            throw new EndpointException();
        projectService.clearEntity(sessionDTO.getUserId());
    }
}