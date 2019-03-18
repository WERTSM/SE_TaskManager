package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.entity.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TasksRepository {
    private static TasksRepository _instance = new TasksRepository();
    private HashMap<UUID, Task> mapTask = new HashMap<>();

    private TasksRepository() {
    }

    public static TasksRepository getInstance() {
        if (_instance == null)
            _instance = new TasksRepository();
        return _instance;
    }

    public void findAll() {
        for (Map.Entry<UUID, Task> item : this.mapTask.entrySet()) {
            item.getValue().viewTask();
        }
    }

    public Collection<Task> findNoAll() {
        return mapTask.values();
    }

    public Task findOne(UUID uuid) {
        return this.mapTask.get(uuid);
    }

    public void persist(UUID uuid, Task task) {
        mapTask.put(uuid, task);
    }

    public void removeAll() {
        this.mapTask.clear();
    }

    public void remove(UUID uuid) {
        mapTask.remove(uuid);
    }
}