package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    void createEntity(@NotNull final Session session, @NotNull String id, @NotNull final Project project);

    @WebMethod
    Project findEntity(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    Collection<Project> findAll(@NotNull final Session session);

    @WebMethod
    Collection<Project> findAllName(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    Collection<Project> findAllDescription(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull final Project project);

    @WebMethod
    void removeEntity(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    void clearEntity(@NotNull final Session session);

}

