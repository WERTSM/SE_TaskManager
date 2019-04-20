package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.dto.ProjectDTO;
import ru.khmelev.tm.dto.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    void createProject(@NotNull final SessionDTO sessionDTO, @NotNull String id, @NotNull final ProjectDTO projectDTO);

    @WebMethod
    ProjectDTO findProject(@NotNull final SessionDTO sessionDTO, @NotNull final String id);

    @WebMethod
    Collection<ProjectDTO> findAllProject(@NotNull final SessionDTO sessionDTO);

    @WebMethod
    Collection<ProjectDTO> findAllNameProject(@NotNull final SessionDTO sessionDTO, @NotNull final String findParameter);

    @WebMethod
    Collection<ProjectDTO> findAllDescriptionProject(@NotNull final SessionDTO sessionDTO, @NotNull final String findParameter);

    @WebMethod
    void editProject(@NotNull final SessionDTO sessionDTO, @NotNull final String id, @NotNull final ProjectDTO projectDTO);

    @WebMethod
    void removeProject(@NotNull final SessionDTO sessionDTO, @NotNull final String id);

    @WebMethod
    void clearProject(@NotNull final SessionDTO sessionDTO);
}

