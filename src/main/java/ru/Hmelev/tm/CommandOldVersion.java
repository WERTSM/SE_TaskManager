package ru.Hmelev.tm;

import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommandOldVersion {
    private SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private BufferedReader reader;

    private ServiceProject serviceProject;
    private ServiceTask serviceTask;

    public CommandOldVersion(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        this.reader = reader;
        this.serviceProject = serviceProject;
        this.serviceTask = serviceTask;
    }

    private String id;
    private String name;
    private String description;
    private String date;

    private Date startDate;
    private Date finishDate;
    private UUID idProject;
    private UUID idTask;
    private UUID idProjectFromTask;

    void projectCreate() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        System.out.println("Name project: ");
        name = reader.readLine();

        System.out.println("Description: ");
        description = reader.readLine();


        System.out.println("Start date: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        idProject = UUID.randomUUID();
        serviceProject.createProject(idProject, name, description, startDate, finishDate);

        System.out.println("!!!DONE!!!");
    }

    void projectShow() throws IOException {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();

        System.out.println("ID project: ");
        id = reader.readLine();

        idProject = UUID.fromString(id);
        serviceProject.showProject(idProject);

        System.out.println("Tasks: ");
        for (Task task : serviceTask.listTaskIdProject(idProject)) {
            task.viewTask();
        }

        System.out.println("!!!DONE!!!");
    }

    void projectEdit() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();

        System.out.println("ID project: ");
        id = reader.readLine();
        idProject = UUID.fromString(id);

        System.out.println("Name project: ");
        name = reader.readLine();

        System.out.println("Description: ");
        description = reader.readLine();

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        serviceProject.editProject(idProject, name, description, startDate, finishDate);

        System.out.println("!!!DONE!!!");
    }

    void projectRemove() throws IOException {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();

        System.out.println("ID project: ");
        id = reader.readLine();
        idProject = UUID.fromString(id);

        serviceTask.listTaskNoIdProject(idProject);
        serviceProject.removeProject(idProject);

        System.out.println("!!!DONE!!!");
    }

    void taskCreate() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        System.out.println("Name task: ");
        name = reader.readLine();

        System.out.println("Description task: ");
        description = reader.readLine();

        System.out.println("Start date task: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date task: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Id project or \'0\': ");
        serviceProject.listProject();

        id = reader.readLine();
        if (id.equals("0")) {
            id = "00000000-0000-0000-0000-000000000000";
            idProjectFromTask = UUID.fromString(id);
        } else
            idProjectFromTask = UUID.fromString(id);

        idTask = UUID.randomUUID();

        serviceTask.createTask(idTask, name, description, startDate, finishDate, idProjectFromTask);

        System.out.println("!!!DONE!!!");
    }

    void taskShow() throws IOException {
        System.out.println("!!!Start command!!!");
        serviceTask.listTask();

        System.out.println("ID task: ");
        id = reader.readLine();
        idTask = UUID.fromString(id);

        serviceTask.showTask(idTask);

        System.out.println("!!!DONE!!!");
    }

    void taskList() {
        System.out.println("!!!Start command!!!");

        serviceTask.listTask();

        System.out.println("!!!DONE!!!");
    }

    void taskEdit() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");
        serviceTask.listTask();

        System.out.println("ID task: ");
        id = reader.readLine();
        idTask = UUID.fromString(id);

        System.out.println("Name task: ");
        name = reader.readLine();

        System.out.println("Description task: ");
        description = reader.readLine();

        System.out.println("Start date task: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date task: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Id project or \'0\': ");

        serviceProject.listProject();

        id = reader.readLine();
        if (id.equals("0")) {
            id = "00000000-0000-0000-0000-000000000000";
            idProjectFromTask = UUID.fromString(id);
        } else {
            idProjectFromTask = UUID.fromString(id);
        }
        serviceTask.editTask(idTask, name, description, startDate, finishDate, idProject);

        System.out.println("!!!DONE!!!");
    }

    void taskRemove() throws IOException {
        System.out.println("!!!Start command!!!");
        serviceTask.listTask();

        System.out.println("ID task: ");
        id = reader.readLine();
        idTask = UUID.fromString(id);

        serviceTask.removeTask(idTask);

        System.out.println("!!!DONE!!!");
    }

    void help() {
        System.out.println("-----------------*********** WELCOME TO TASK MANAGER ************-----------------\n"
                + "\"help\" : Show all commands.\n\n"
                + "\"project-create\" : Create new project.\n"
                + "\"project-clear\" : Remove all projects.\n"
                + "\"project-list\"\" : Show all projects.\n"
                + "\"project-show\" : Show selected project.\n"
                + "\"project-edit\" : Edit selected project\n"
                + "\"project-remove\" : Remove selected project.\n\n"
                + "\"task-create\" : Create new task.\n"
                + "\"task-clear\" : Remove all tasks.\n"
                + "\"task-list\"\" : Show all tasks.\n"
                + "\"task-show\" : Show selected project.\n"
                + "\"task-edit\" : Edit selected task\n"
                + "\"task-remove\" : Remove selected task.\n"
                + "\"exit\" : Exit from the program.\n\n"
                + "----------------- ********************************************** -----------------\n");
    }

    void projectClear() {
        System.out.println("!!!Start command!!!");
        serviceProject.clearProject();
        System.out.println("!!!DONE!!!");
    }

    void taskClear() {
        System.out.println("!!!Start command!!!");
        serviceTask.clearTask();
        System.out.println("!!!DONE!!!");
    }

    void exit() {
        System.exit(0);
    }

    void projectList() {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();
        System.out.println("!!!DONE!!!");
    }
}