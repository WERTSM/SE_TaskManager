package ru.Hmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.util.List;

public interface InterfaceTaskService extends EntityService<Task> {
    List<Task> listTaskFromProject(@NotNull final String idProject, @NotNull final User user);

    void removeAllTaskFromProject(@NotNull final String idProject, @NotNull final User user);
}
