package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;
import java.util.List;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    void createEntity(@NotNull final Session session, @NotNull String id, @NotNull final Task task);

    @WebMethod
    Task findEntity(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    Collection<Task> findAll(@NotNull final Session session);

    @WebMethod
    Collection<Task> findAllName(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    Collection<Task> findAllDescription(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull final Task task);

    @WebMethod
    void removeEntity(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    void clearEntity(@NotNull final Session session);

    List<Task> listTaskFromProject(@NotNull Session session, @NotNull String idProject);

    void removeAllTaskFromProject(@NotNull Session session, @NotNull String idProject);
}