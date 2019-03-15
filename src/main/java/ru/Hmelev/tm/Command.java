package ru.Hmelev.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Command {
    private List<Project> projectsList = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();
    private BufferedReader reader;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private int idProject;
    private int idTask;

    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;

    private Project project;
    private Task task;

    public Command(BufferedReader reader) {
        this.reader = reader;
    }

    public void projectCreate() throws IOException, ParseException {
        System.out.println("Name project: ");
        name = reader.readLine();
        System.out.println("Description: ");
        description = reader.readLine();
        System.out.println("Start date: \"dd.MM.yyyy\" ");
        startDate = dateFormat.parse(reader.readLine());
        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        finishDate = dateFormat.parse(reader.readLine());
        idProject++;
        project = new Project(idProject, name, description, startDate, finishDate);
        projectsList.add(project);
        System.out.println("!!!DONE!!!");
    }

    public void projectShow() throws IOException {
        System.out.println("Id project: ");
        idProject = Integer.parseInt(reader.readLine());
        for (Project project : projectsList) {
            if (project.getId() == idProject)
                this.project = project;
        }
        project.viewProject();
        System.out.println("!!!DONE!!!");
    }

    public void projectList() {
        for (Project project : projectsList) {
            project.viewProject();
        }
        System.out.println("!!!DONE!!!");
    }

    public void projectEdit() throws IOException, ParseException {
        System.out.println("Id project: ");
        idProject = Integer.parseInt(reader.readLine());
        for (Project project : projectsList) {
            if (project.getId() == idProject)
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

    public void projectRemove() throws IOException {
        System.out.println("Id project: ");
        idProject = Integer.parseInt(reader.readLine());
        for (Project project : projectsList) {
            if (project.getId() == idProject)
                this.project = project;
        }
        idProject--;
        projectsList.remove(project);
        System.out.println("!!!DONE!!!");
    }

    public void taskCreate() throws IOException, ParseException {
        System.out.println("Name task: ");
        name = reader.readLine();
        System.out.println("Description: ");
        description = reader.readLine();
        System.out.println("Start date: \"dd.MM.yyyy\" ");
        startDate = dateFormat.parse(reader.readLine());
        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        finishDate = dateFormat.parse(reader.readLine());
        idTask++;
        task = new Task(idTask, name, description, startDate, finishDate);
        taskList.add(task);
        System.out.println("!!!DONE!!!");
    }

    public void taskShow() throws IOException {
        System.out.println("Id task: ");
        idTask = Integer.parseInt(reader.readLine());
        for (Task task : taskList) {
            if (task.getId() == idTask)
                this.task = task;
        }
        task.viewTask();
        System.out.println("!!!DONE!!!");
    }

    public void taskList() {
        for (Task task : taskList) {
            task.viewTask();
        }
        System.out.println("!!!DONE!!!");
    }

    public void taskEdit() throws IOException, ParseException {
        System.out.println("Id task: ");
        idTask = Integer.parseInt(reader.readLine());
        for (Task task : taskList) {
            if (task.getId() == idTask)
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
        System.out.println("!!!DONE!!!");
    }

    public void taskRemove() throws IOException {
        System.out.println("Id task: ");
        idTask = Integer.parseInt(reader.readLine());
        for (Task task : taskList) {
            if (task.getId() == idTask)
                this.task = task;
        }
        idTask--;
        taskList.remove(task);
        System.out.println("!!!DONE!!!");
    }

    public void help() {
        System.out.println("-----------------***********WELCOME TO TASK MANAGER************-----------------\n");
        System.out.println("\"help\" : Show all commands.\n");

        System.out.println("\"project-create\" : Create new project.");
        System.out.println("\"project-clear\" : Remove all projects.");
        System.out.println("\"project-list\"\" : Show all projects.");
        System.out.println("\"project-show\" : Show selected project.");
        System.out.println("\"project-edit\" : Edit selected project");
        System.out.println("\"project-remove\" : Remove selected project.\n");
        System.out.println("\"task-create\" : Create new task.");
        System.out.println("\"task-clear\" : Remove all tasks.");
        System.out.println("\"task-list\"\" : Show all tasks.");
        System.out.println("\"task-show\" : Show selected project.");
        System.out.println("\"task-edit\" : Edit selected task");
        System.out.println("\"task-remove\" : Remove selected task.\n");
        System.out.println("-----------------**********************************************-----------------\n");
    }

    public void projectClear() {
        projectsList.clear();
        System.out.println("!!!DONE!!!");
    }

    public void taskClear() {
        taskList.clear();
        System.out.println("!!!DONE!!!");
    }
}