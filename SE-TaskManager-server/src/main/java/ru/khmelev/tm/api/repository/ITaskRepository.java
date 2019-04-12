package ru.khmelev.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository extends IEntityRepository<Task> {

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId);

    @NotNull Collection<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId);
}