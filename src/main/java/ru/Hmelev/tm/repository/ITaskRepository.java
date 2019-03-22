package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository {
    void persist(String id, Task task);

    Task findOne(String id);

    Collection<Task> findAll();

    void merge();

    void remove(String id);

    void removeAll();
}