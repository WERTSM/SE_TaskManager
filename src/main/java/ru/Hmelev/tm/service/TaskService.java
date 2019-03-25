package ru.Hmelev.tm.service;

import ru.Hmelev.tm.api.ITaskRepository;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.util.*;

public final class TaskService extends Service {
    private final ITaskRepository taskRepository;
    private Task task;
    private String userId;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(String name, String description, Date startDate, Date finishDate, String idProject, User user) {
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null && idProject != null && !idProject.isEmpty() && user != null) {
            String id = UUID.randomUUID().toString();
            userId = user.getId();
            task = new Task(id, name, description, startDate, finishDate, idProject, userId);
            taskRepository.persist(id, task);
        }
    }

    public Task findTask(String id, User user) {
        if (id != null && !id.isEmpty()) {
            userId = user.getId();
            return taskRepository.findOne(id, userId);
        }
        return null;
    }

    public Collection<Task> findAllTasks(User user) {
        if (user != null) {
            userId = user.getId();
            return taskRepository.findAll(userId);
        }
        return null;
    }

    public void editTask(String id, String name, String description, Date startDate, Date finishDate, String idProjectFromTask, String userId) {
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty()
                && startDate != null && finishDate != null && idProjectFromTask != null && !idProjectFromTask.isEmpty()) {
            task = taskRepository.findOne(id, userId);
            task.setName(name);
            task.setDescription(description);
            task.setStartDate(startDate);
            task.setFinishDate(finishDate);
            task.setIdProject(idProjectFromTask);
        }
    }

    public void removeTask(String id, User user) {
        if (id != null && !id.isEmpty() && user != null) {
            userId = user.getId();
            taskRepository.remove(id, userId);
        }
    }

    public void clearTask(User user) {
        if (user != null) {
            userId = user.getId();
            taskRepository.removeAll(userId);
        }
    }

    public List<Task> listTaskFromProject(String idProject, User user) {
        if (idProject != null && !idProject.isEmpty() && user != null) {
            userId = user.getId();
            List<Task> list = new ArrayList<>(taskRepository.findAll(userId));
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                task = it.next();
                if (!task.getIdProject().equals(idProject)) {
                    it.remove();
                }
            }
            return list;
        }
        return null;
    }

    public void removeAllTaskFromProject(String idProject, User user) {
        if (idProject != null && !idProject.isEmpty() && user != null) {
            userId = user.getId();
            List<Task> list = new ArrayList<>(taskRepository.findAll(userId));
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                task = it.next();
                if (task.getIdProject().equals(idProject)) {
                    taskRepository.remove(task.getId(), userId);
                }
            }
        }
    }
}