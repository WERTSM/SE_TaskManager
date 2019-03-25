package ru.Hmelev.tm.api;

import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.util.Collection;

public interface ITaskRepository {
    void persist(String id, Task task);

    Task findOne(String id, String userId);

    Collection<Task> findAll(String userId);

    void merge(String taskId, String user);

    void remove(String id, String user);

    void removeAll(String user);
}