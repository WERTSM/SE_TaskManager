package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import javax.persistence.QueryHint;
import java.util.Collection;

@Repository(forEntity = Task.class)
public interface ITaskRepository {

    void persist(@NotNull final Task task);

    @NotNull
    @Query(value = "Select task from Task task where task.id = :id and userId = :userId", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Task findById(@NotNull @QueryParam("id") final String id, @NotNull @QueryParam("userId") final String userId);

    @NotNull
    @Query(value = "Select task from Task task where userId = :userId", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Collection<Task> findAll(@NotNull @QueryParam("userId") final String userId);

    void merge(@NotNull final Task task);

    void remove(@NotNull final Task task);

    @Modifying
    @Query(value = "DELETE FROM Task WHERE userId = :userId")
    void removeAll(@NotNull @QueryParam("userId") final String userId);

    @Query(value = "Select task from Task task where projectId = :projectId and userId = :userId", hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Collection<Task> findAllTaskFromProject(@NotNull @QueryParam("projectId") final String projectId, @NotNull @QueryParam("userId") final String userId);
}