package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TasksRepository {
    private Map<String, Task> mapTask = new HashMap<>();

    public Collection<Task> findAll() {
        return mapTask.values();
    }

    public Task findOne(String id) {
        if (id != null && !id.isEmpty()) {
            return this.mapTask.get(id);
        }
        return null;
    }

    public void persist(String id, Task task) {
        if (id != null && !id.isEmpty() && task != null) {
            mapTask.put(id, task);
        }
    }

    public void removeAll() {
        this.mapTask.clear();
    }

    public void remove(String id) {
        if (id != null && !id.isEmpty()) {
            mapTask.remove(id);
        }
    }
}