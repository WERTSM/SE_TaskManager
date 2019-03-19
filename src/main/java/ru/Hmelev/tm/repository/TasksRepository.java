package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Task;

import java.util.Collection;
import java.util.HashMap;

public class TasksRepository {
    private HashMap<String, Task> mapTask = new HashMap<>();

    public Collection<Task> findAll() {
        return mapTask.values();
    }

    public Task findOne(String id) {
        return this.mapTask.get(id);
    }

    public void persist(String id, Task task) {
        mapTask.put(id, task);
    }

    public void removeAll() {
        this.mapTask.clear();
    }

    public void remove(String id) {
        mapTask.remove(id);
    }
}