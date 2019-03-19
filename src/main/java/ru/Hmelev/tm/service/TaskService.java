package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.repository.TasksRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TaskService extends Service {
    private TasksRepository taskRepository;
    private Task task;

    private List<Task> list = new ArrayList<>();

    public TaskService(TasksRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(String id, String name, String description, Date startDate, Date finishDate, String idProject) {
        task = new Task(id, name, description, startDate, finishDate, idProject);
        taskRepository.persist(id, task);
    }

    public void clearTask() {
        taskRepository.removeAll();
    }

    public void listTask() {
        for (Task task : taskRepository.findAll()) {
            showTask(task.getId());
        }

    }

    public void editTask(String id, String name, String description, Date startDate, Date finishDate, String idProject) {
        task = taskRepository.findOne(id);
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        task.setIdProject(idProject);
    }

    public void showTask(String id) {
        Task task = taskRepository.findOne(id);
        if (task != null) {
            System.out.println(
                    "[ ID = " + task.getId()
                            + "; Name = " + task.getName()
                            + "; Description = " + task.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(task.getStartDate())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(task.getFinishDate())
                            + "; idProject = " + task.getIdProject()
                            + " ]");
        }
    }

    public void removeTask(String id) {
        taskRepository.remove(id);
    }

    public List<Task> listTaskIdProject(String idProject) {
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

    public void listTaskNoIdProject(String idProject) {
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