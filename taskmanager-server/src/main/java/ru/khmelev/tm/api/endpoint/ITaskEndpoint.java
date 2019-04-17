package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    void createTask(@NotNull final Session session, @NotNull String id, @NotNull final Task task);

    @WebMethod
    Task findTask(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    Collection<Task> findAllTAsk(@NotNull final Session session);

    @WebMethod
    Collection<Task> findAllNameTask(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    Collection<Task> findAllDescriptionTask(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    void editEntityTask(@NotNull final Session session, @NotNull final String id, @NotNull final Task task);

    @WebMethod
    void removeTask(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    void clearTask(@NotNull final Session session);

    Collection<Task> listTaskFromProject(@NotNull Session session, @NotNull String projectId);

    void removeAllTaskFromProject(@NotNull Session session, @NotNull String projectId);
}