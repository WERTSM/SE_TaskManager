package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Task;

import java.util.List;

public interface InterfaceTaskService extends EntityService<Task> {
    List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final String userId);

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final String userId);
}
