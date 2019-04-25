package ru.khmelev.tm.api.repository;

import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;

@Repository(forEntity = Task.class)
public interface ITaskRepository {

    void persist(@NotNull final Task task);

    @NotNull Task findOne(@NotNull final String id, @NotNull final String userId);

    @NotNull Collection<Task> findAll(@NotNull final String userId);

    void merge(@NotNull final Task task);

    void remove(@NotNull final Task task);

    void removeAll(@NotNull final String userId);

    Collection<Task> findAllTaskFromProject(@NotNull final String projectId, @NotNull final String userId);
}