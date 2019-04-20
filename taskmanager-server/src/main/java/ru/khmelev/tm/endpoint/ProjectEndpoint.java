package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.dto.SessionDTO;

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
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "project") @NotNull final ProjectDTO projectDTO
    ) {
        sessionService.checkSession(sessionDTO);
        projectService.createEntity(id, projectDTO);
    }

    @Override
    public ProjectDTO findProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(sessionDTO);
        return projectService.findEntity(id, sessionDTO.getUserId());
    }

    @Override
    public Collection<ProjectDTO> findAllProject(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        return projectService.findAll(sessionDTO.getUserId());
    }

    @Override
    public Collection<ProjectDTO> findAllNameProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(sessionDTO);
        return projectService.findAllName(findParameter, sessionDTO.getUserId());
    }

    @Override
    public Collection<ProjectDTO> findAllDescriptionProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(sessionDTO);
        return projectService.findAllDescription(findParameter, sessionDTO.getUserId());
    }

    @Override
    public void editProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "project") @NotNull ProjectDTO projectDTO
    ) {
        sessionService.checkSession(sessionDTO);
        projectService.editEntity(id, projectDTO, sessionDTO.getUserId());
    }

    @Override
    public void removeProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(sessionDTO);
        projectService.removeEntity(id, sessionDTO.getUserId());
    }

    @Override
    public void clearProject(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        projectService.clearEntity(sessionDTO.getUserId());
    }
}