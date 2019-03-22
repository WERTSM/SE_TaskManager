package ru.Hmelev.tm.command;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
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
    protected String userId;

    protected String login;
    protected String password;
    protected String role;
    protected ServiceLocator serviceLocator;
    protected Security security;
    private String nameCommand;
    private String descriptionCommand;
    private Role roleCommand;
    public Command(ServiceLocator serviceLocator, String nameCommand, String descriptionCommand, Security security) {
        this.serviceLocator = serviceLocator;
        this.reader = serviceLocator.getReader();
        this.projectService = serviceLocator.getProjectService();
        this.taskService = serviceLocator.getTaskService();
        this.userService = serviceLocator.getUserService();
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
        this.security = security;
    }

    public Command(ServiceLocator serviceLocator, String nameCommand, String descriptionCommand, Security security, Role role) {
        this.serviceLocator = serviceLocator;
        this.reader = serviceLocator.getReader();
        this.projectService = serviceLocator.getProjectService();
        this.taskService = serviceLocator.getTaskService();
        this.userService = serviceLocator.getUserService();
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
        this.security = security;
        this.roleCommand = role;
    }

    public Command(String nameCommand, String descriptionCommand, Security security) {
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
        this.security = security;
    }

    public Security getSecurity() {
        return security;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public String getDescriptionCommand() {
        return descriptionCommand;
    }

    public Role getRoleCommand() {
        return roleCommand;
    }

    public void execute() throws IOException, ParseException {
    }
}