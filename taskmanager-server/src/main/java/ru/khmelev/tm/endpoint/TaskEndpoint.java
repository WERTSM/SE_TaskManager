package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.dto.SessionDTO;
import ru.khmelev.tm.entity.dto.TaskDTO;

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
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "task") @NotNull final TaskDTO taskDTO
    ) {
        sessionService.checkSession(sessionDTO);
        taskService.createEntity(id, taskDTO);
    }

    @Override
    public TaskDTO findTask(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(sessionDTO);
        return taskService.findEntity(id, sessionDTO.getUserId());
    }

    @Override
    public Collection<TaskDTO> findAllTAsk(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        return taskService.findAll(sessionDTO.getUserId());
    }

    @Override
    public Collection<TaskDTO> findAllNameTask(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(sessionDTO);
        return taskService.findAllName(findParameter, sessionDTO.getUserId());
    }

    @Override
    public Collection<TaskDTO> findAllDescriptionTask(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "findParameter") @NotNull String findParameter
    ) {
        sessionService.checkSession(sessionDTO);
        return taskService.findAllDescription(findParameter, sessionDTO.getUserId());
    }

    @Override
    public void editEntityTask(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id,
            @WebParam(name = "task") @NotNull TaskDTO taskDTO
    ) {
        sessionService.checkSession(sessionDTO);
        taskService.editEntity(id, taskDTO, sessionDTO.getUserId());
    }

    @Override
    public void removeTask(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "id") @NotNull final String id
    ) {
        sessionService.checkSession(sessionDTO);
        taskService.removeEntity(id, sessionDTO.getUserId());
    }

    @Override
    public void clearTask(@WebParam(name = "session") @NotNull final SessionDTO sessionDTO) {
        sessionService.checkSession(sessionDTO);
        taskService.clearEntity(sessionDTO.getUserId());
    }

    @Override
    public Collection<TaskDTO> listTaskFromProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "projectId") @NotNull final String projectId
    ) {
        sessionService.checkSession(sessionDTO);
        return taskService.listTaskFromProject(projectId, sessionDTO.getUserId());
    }

    @Override
    public void removeAllTaskFromProject(
            @WebParam(name = "session") @NotNull final SessionDTO sessionDTO,
            @WebParam(name = "projectId") @NotNull final String projectId
    ) {
        sessionService.checkSession(sessionDTO);
        taskService.removeAllTaskFromProject(projectId, sessionDTO.getUserId());
    }
}