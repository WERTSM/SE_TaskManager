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
    void createProject(@NotNull final Session session, @NotNull String id, @NotNull final Project project);

    @WebMethod
    Project findProject(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    Collection<Project> findAllProject(@NotNull final Session session);

    @WebMethod
    Collection<Project> findAllNameProject(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    Collection<Project> findAllDescriptionProject(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    void editProject(@NotNull final Session session, @NotNull final String id, @NotNull final Project project);

    @WebMethod
    void removeProject(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    void clearProject(@NotNull final Session session);

}

