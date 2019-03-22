package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class TaskRepository implements ITaskRepository {
    private Map<String, Task> mapTask = new HashMap<>();

    @Override
    public void persist(String id, Task task) {
        if (id != null && !id.isEmpty() && task != null) {
            mapTask.put(id, task);
        }
    }

    @Override
    public Task findOne(String id) {
        if (id != null && !id.isEmpty()) {
            return this.mapTask.get(id);
        }
        return null;
    }

    @Override
    public Collection<Task> findAll() {
        return mapTask.values();
    }

    @Override
    public void merge() {

    }

    @Override
    public void remove(String id) {
        if (id != null && !id.isEmpty()) {
            mapTask.remove(id);
        }
    }

    @Override
    public void removeAll() {
        this.mapTask.clear();
    }
}