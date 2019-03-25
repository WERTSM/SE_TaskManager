package ru.Hmelev.tm.repository;

import ru.Hmelev.tm.api.ITaskRepository;
import ru.Hmelev.tm.entity.Task;

import java.util.*;

public final class TaskRepository implements ITaskRepository {
    private Map<String, Task> mapTask = new HashMap<>();

    @Override
    public void persist(String id, Task task) {
        if (id != null && !id.isEmpty() && task != null) {
            mapTask.put(id, task);
        }
    }

    @Override
    public Task findOne(String id, String userId) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            Collection<Task> list = new ArrayList<>(findAll(userId));
            for (Task task : list) {
                if (task.getId().equals(id)) {
                    return task;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Task> findAll(String userId) {
        if (userId != null && !userId.isEmpty()) {
            Collection<Task> list = mapTask.values();
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (!task.getUserId().equals(userId)) {
                    it.remove();
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public void merge(String id, String userId) {
    }

    @Override
    public void remove(String id, String userId) {
        if (id != null && !id.isEmpty() && userId != null && !userId.isEmpty()) {
            Collection<Task> list = mapTask.values();
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (task.getId().equals(id) && task.getUserId().equals(userId)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    @Override
    public void removeAll(String userId) {
        if (userId != null && !userId.isEmpty()) {
            Collection<Task> list = mapTask.values();
            list.clear();
        }
    }
}