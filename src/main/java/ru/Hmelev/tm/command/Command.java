package ru.Hmelev.tm.command;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public abstract class Command {
    protected UserService userService;
    protected BufferedReader reader;
    protected ProjectService projectService;
    protected TaskService taskService;
    protected String name;
    protected String description;
    protected String id;
    protected String date;
    protected Date startDate;
    protected Date finishDate;
    protected String idProject;
    protected String idTask;
    protected String idProjectFromTask;

    protected String login;
    protected String password;
    protected String role;

    private String nameCommand;
    private String descriptionCommand;

    public Command(Bootstrap bootstrap, String nameCommand, String descriptionCommand) {
        this.reader = bootstrap.getReader();
        this.projectService = bootstrap.getProjectService();
        this.taskService = bootstrap.getTaskService();
        this.userService = bootstrap.getUserService();
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
    }

    public Command(String nameCommand, String descriptionCommand) {
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public String getDescriptionCommand() {
        return descriptionCommand;
    }

    public void execute() throws IOException, ParseException {
    }
}