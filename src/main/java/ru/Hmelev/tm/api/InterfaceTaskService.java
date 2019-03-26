package ru.Hmelev.tm.api;


import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.util.List;

public interface InterfaceTaskService extends EntityService<Task> {
    List<Task> listTaskFromProject(final String idProject, final User user);

    void removeAllTaskFromProject(final String idProject, final User user);
}
