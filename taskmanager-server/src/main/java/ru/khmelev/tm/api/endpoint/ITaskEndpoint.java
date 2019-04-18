package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.dto.SessionDTO;
import ru.khmelev.tm.entity.dto.TaskDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    void createTask(@NotNull final SessionDTO sessionDTO, @NotNull String id, @NotNull final TaskDTO task);

    @WebMethod
    TaskDTO findTask(@NotNull final SessionDTO sessionDTO, @NotNull final String id);

    @WebMethod
    Collection<TaskDTO> findAllTAsk(@NotNull final SessionDTO sessionDTO);

    @WebMethod
    Collection<TaskDTO> findAllNameTask(@NotNull final SessionDTO sessionDTO, @NotNull final String findParameter);

    @WebMethod
    Collection<TaskDTO> findAllDescriptionTask(@NotNull final SessionDTO sessionDTO, @NotNull final String findParameter);

    @WebMethod
    void editEntityTask(@NotNull final SessionDTO sessionDTO, @NotNull final String id, @NotNull final TaskDTO taskDTO);

    @WebMethod
    void removeTask(@NotNull final SessionDTO sessionDTO, @NotNull final String id);

    @WebMethod
    void clearTask(@NotNull final SessionDTO sessionDTO);

    Collection<TaskDTO> listTaskFromProject(@NotNull SessionDTO sessionDTO, @NotNull String projectId);

    void removeAllTaskFromProject(@NotNull SessionDTO sessionDTO, @NotNull String projectId);
}