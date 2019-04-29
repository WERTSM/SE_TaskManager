package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;

import java.util.Collection;

@Repository(forEntity = Project.class)
public interface IProjectRepository {

    void persist(@NotNull Project project);

    @NotNull
    @Query(value = "Select project from Project project where project.id = :id and userId = :userId")
    Project findById(@NotNull @QueryParam("id") final String id, @NotNull @QueryParam("userId") final String userId);

    @NotNull
    @Query(value = "Select project from Project project where userId = :userId")
    Collection<Project> findAll(@NotNull @QueryParam("userId") final String userId);

    void merge(@NotNull final Project project);

    void remove(@NotNull final Project project);

    @Query(value = "DELETE FROM Project project WHERE project.userId = :userId")
    void removeAll(@NotNull @QueryParam("userId") final String userId);
}