package ru.Hmelev.tm;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Command {
    public Command(BufferedReader reader) {
        this.reader = reader;
    }

    void projectCreate() throws IOException, ParseException {
        System.out.println("Name project: ");
        name = reader.readLine();
        System.out.println("Description: ");
        description = reader.readLine();
        System.out.println("Start date: \"dd.MM.yyyy\" ");
        startDate = dateFormat.parse(reader.readLine());
        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        finishDate = dateFormat.parse(reader.readLine());
        idProject = UUID.randomUUID();
        project = new Project(idProject, name, description, startDate, finishDate);
        projectsList.add(project);
        System.out.println("!!!DONE!!!");
    }

    void projectShow() throws IOException {
        System.out.println("Id project: ");
        idProject = UUID.fromString(reader.readLine());
        for (Project project : projectsList) {
            if (project.getId().equals(idProject))
                this.project = project;
        }
        project.viewProject();
        System.out.println("Tasks: ");
        for (Task task : getTaskListIdProject(idProject)) {
            task.viewTask();
        }
        System.out.println("!!!DONE!!!");
    }

    void projectList() {
        for (Project project : projectsList) {
            project.viewProject();
        }
        System.out.println("!!!DONE!!!");
    }

    void projectEdit() throws IOException, ParseException {
        System.out.println("Id project: ");
        idProject = UUID.fromString(reader.readLine());
        for (Project project : projectsList) {
            if (project.getId().equals(idProject))
                this.project = project;
        }
        System.out.println("Name project: ");
        name = reader.readLine();
        project.setName(name);
        System.out.println("Description: ");
        description = reader.readLine();
        project.setDescription(description);
        System.out.println("Start date: \"dd.MM.yyyy\" ");
        startDate = dateFormat.parse(reader.readLine());
        project.setStartDate(startDate);
        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        finishDate = dateFormat.parse(reader.readLine());
        project.setFinishDate(finishDate);
        System.out.println("!!!DONE!!!");
    }

    void projectRemove() throws IOException {
        System.out.println("Id project: ");
        idProject = UUID.fromString(reader.readLine());

        for (Project project : projectsList) {
            if (project.getId().equals(idProject))
                this.project = project;
        }

        for (Task task : getTaskListIdProject(idProject)) {
            taskList.remove(task);
        }

        taskListIdProject.clear();
        projectsList.remove(project);
        System.out.println("!!!DONE!!!");
    }

    void taskCreate() throws IOException, ParseException {
        System.out.println("Name task: ");
        name = reader.readLine();
        System.out.println("Description task: ");
        description = reader.readLine();
        System.out.println("Start date: \"dd.MM.yyyy\" ");
        startDate = dateFormat.parse(reader.readLine());
        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        finishDate = dateFormat.parse(reader.readLine());
        System.out.println("Id project in the task: ");
        idProjectFromTask = UUID.fromString(reader.readLine());
        idTask = UUID.randomUUID();
        task = new Task(idTask, name, description, startDate, finishDate, idProjectFromTask);
        taskList.add(task);
        System.out.println("!!!DONE!!!");
    }

    void taskShow() throws IOException {
        System.out.println("Id task: ");
        idTask = UUID.fromString(reader.readLine());
        for (Task task : taskList) {
            if (task.getId().equals(idTask))
                this.task = task;
        }
        task.viewTask();
        System.out.println("!!!DONE!!!");
    }

    void taskList() {
        for (Task task : taskList) {
            task.viewTask();
        }
        System.out.println("!!!DONE!!!");
    }

    void taskEdit() throws IOException, ParseException {
        System.out.println("Id task: ");
        idTask = UUID.fromString(reader.readLine());
        for (Task task : taskList) {
            if (task.getId().equals(idTask))
                this.task = task;
        }
        System.out.println("Name task: ");
        name = reader.readLine();
        task.setName(name);
        System.out.println("Description task: ");
        description = reader.readLine();
        task.setDescription(description);
        System.out.println("Start date: \"dd.MM.yyyy\" ");
        startDate = dateFormat.parse(reader.readLine());
        task.setStartDate(startDate);
        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        finishDate = dateFormat.parse(reader.readLine());
        task.setFinishDate(finishDate);
        System.out.println("Id project in the task: ");
        idProjectFromTask = UUID.fromString(reader.readLine());
        task.setIdProject(idProjectFromTask);
        System.out.println("!!!DONE!!!");
    }

    void taskRemove() throws IOException {
        System.out.println("Id task: ");
        idTask = UUID.fromString(reader.readLine());
        for (Task task : taskList) {
            if (task.getId().equals(idTask))
                this.task = task;
        }
        taskList.remove(task);
        System.out.println("!!!DONE!!!");
    }

    void help() {
        System.out.println("-----------------*********** WELCOME TO TASK MANAGER ************-----------------\n" +
                "\"help\" : Show all commands.\n\n" +
                "\"project-create\" : Create new project.\n" +
                "\"project-clear\" : Remove all projects.\n" +
                "\"project-list\"\" : Show all projects.\n" +
                "\"project-show\" : Show selected project.\n" +
                "\"project-edit\" : Edit selected project\n" +
                "\"project-remove\" : Remove selected project.\n\n" +
                "\"task-create\" : Create new task.\n" +
                "\"task-clear\" : Remove all tasks.\n" +
                "\"task-list\"\" : Show all tasks.\n" +
                "\"task-show\" : Show selected project.\n" +
                "\"task-edit\" : Edit selected task\n" +
                "\"task-remove\" : Remove selected task.\n\n" +
                "----------------- ********************************************** -----------------\n");
    }

    void projectClear() {
        projectsList.clear();
        System.out.println("!!!DONE!!!");
    }

    void taskClear() {
        taskList.clear();
        System.out.println("!!!DONE!!!");
    }

    private List<Task> getTaskListIdProject(UUID idProject) {
        for (Task task : taskList) {
            if (task.getIdProject().equals(idProject))
                taskListIdProject.add(task);
        }
        return taskListIdProject;
    }

    void exit() {
        System.exit(0);
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private BufferedReader reader;

    private Project project;
    private List<Project> projectsList = new ArrayList<>();

    private Task task;
    private List<Task> taskList = new ArrayList<>();
    private List<Task> taskListIdProject = new ArrayList<>();

    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;

    private UUID idProject;
    private UUID idTask;
    private UUID idProjectFromTask;
}