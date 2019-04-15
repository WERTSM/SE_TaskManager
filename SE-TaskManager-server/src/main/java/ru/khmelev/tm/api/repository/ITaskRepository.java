package ru.khmelev.tm.api.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository extends IEntityRepository<Task> {

    void persist(@NotNull String id, @NotNull Task task);

    @NotNull
    @SneakyThrows
    Collection<Task> findAll(@NotNull String userId);

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId);

    @NotNull Collection<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId);
}