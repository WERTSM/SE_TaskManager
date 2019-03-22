package ru.Hmelev.tm.service;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Task;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ITaskService {
    void createTask(String name, String description, Date startDate, Date finishDate, String idProject, String userId);
    Task findTask(String id);
    Collection<Task> findAllTasks(String userId);
    void editTask(String id, String name, String description, Date startDate, Date finishDate, String idProjectFromTask, String idProject);
    void removeTask(String id);
    void clearTask();
    List<Task> listTaskIdProject(String idProject);
    void listTaskNoIdProject(String idProject);
}