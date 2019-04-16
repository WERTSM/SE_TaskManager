package ru.khmelev.tm.api.repository.mybatis;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepositoryMyBatis {

    void persist(@NotNull String id, @NotNull Task task);

    @NotNull Task findOne(@NotNull final String id, @NotNull final String userId);

    @NotNull Collection<Task> findAll(@NotNull final String userId);

    void merge(@NotNull final String id, @NotNull final Task task, @NotNull final String userId);

    void remove(@NotNull final String id, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId);

    @NotNull Collection<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId);
}