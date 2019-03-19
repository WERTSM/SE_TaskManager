package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.repository.TasksRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Iterator;

public class TaskService {
    private TasksRepository taskRepository;
    private Task task;

    private List<Task> list = new ArrayList<>();

    public TaskService(TasksRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(UUID id, String name, String description, Date startDate, Date finishDate, UUID idProject) {
        task = new Task(id, name, description, startDate, finishDate, idProject);
        taskRepository.persist(id, task);
    }

    public void clearTask() {
        taskRepository.removeAll();
    }

    public void listTask() {
        taskRepository.findAll();
    }

    public void editTask(UUID id, String name, String description, Date startDate, Date finishDate, UUID idProject) {
        task = taskRepository.findOne(id);
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        task.setIdProject(idProject);
    }

    public void showTask(UUID id) {
        Task pr = taskRepository.findOne(id);
        pr.viewTask();
    }

    public void removeTask(UUID id) {
        taskRepository.remove(id);
    }

    public List<Task> listTaskIdProject(UUID idProject) {
        list.addAll(taskRepository.findNoAll());
        Iterator<Task> it = list.iterator();
        while (it.hasNext()) {
            task = it.next();
            if (!task.getIdProject().equals(idProject)) {
                it.remove();
            }
        }
        return list;
    }

    public void listTaskNoIdProject(UUID idProject) {
        list.addAll(taskRepository.findNoAll());
        Iterator<Task> it = list.iterator();
        while (it.hasNext()) {
            task = it.next();
            if (task.getIdProject().equals(idProject)) {
                taskRepository.remove(task.getId());
            }
        }
    }
}