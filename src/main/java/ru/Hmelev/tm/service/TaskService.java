package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.repository.ITaskRepository;

import java.util.*;

public class TaskService extends Service {
    private final ITaskRepository taskRepository;
    private final List<Task> list = new ArrayList<>();
    private Task task;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(String name, String description, Date startDate, Date finishDate, String idProject, String userId) {
        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null && idProject != null && !idProject.isEmpty()) {
            String id = UUID.randomUUID().toString();
            task = new Task(id, name, description, startDate, finishDate, idProject, userId);
            taskRepository.persist(id, task);
        }
    }

    public Task findTask(String id) {
        if (id != null && !id.isEmpty()) {
            return taskRepository.findOne(id);
        }
        return null;
    }

    public Collection<Task> findAllTasks(String userId) {
        Collection<Task> list = new ArrayList<>(taskRepository.findAll());
        Iterator<Task> it = list.iterator();
        while (it.hasNext()) {
            task = it.next();
            if (!task.getUserId().equals(userId)) {
                it.remove();
            }
        }
        return list;
    }

    public void editTask(String id, String name, String description, Date startDate, Date finishDate, String idProjectFromTask, String idProject) {
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty()
                && startDate != null && finishDate != null && idProject != null && !idProject.isEmpty()) {
            task = taskRepository.findOne(id);
            task.setName(name);
            task.setDescription(description);
            task.setStartDate(startDate);
            task.setFinishDate(finishDate);
            task.setIdProject(idProject);
        }
    }



    public void removeTask(String id) {
        taskRepository.remove(id);
    }

    public void clearTask() {
        taskRepository.removeAll();
    }

    public List<Task> listTaskIdProject(String idProject) {
        if (idProject != null && !idProject.isEmpty()) {
            list.addAll(taskRepository.findAll());
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

    public void listTaskNoIdProject(String idProject) {
        if (idProject != null && !idProject.isEmpty()) {
            list.addAll(taskRepository.findAll());
            Iterator<Task> it = list.iterator();
            while (it.hasNext()) {
                task = it.next();
                if (task.getIdProject().equals(idProject)) {
                    taskRepository.remove(task.getId());
                }
            }
        }
    }
}