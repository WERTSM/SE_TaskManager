package ru.Hmelev.tm.command;

import com.google.common.base.Strings;
import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    protected SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

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

    protected static boolean isStringValid(String str) {
        if (Strings.isNullOrEmpty(str)) {
            System.err.println("Введите название!");
            return false;
        } else {
            return true;
        }
    }

    protected boolean isDateValid(String date) {
        DEFAULT_DATE_FORMAT.setLenient(false);
        try {
            DEFAULT_DATE_FORMAT.parse(date);
            return true;
        } catch (Exception e) {
            System.err.println("Введите правильно дату. Например: 13.03.2019");
            return false;
        }
    }

    protected boolean isUUIDValid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Введите правильно UUID. Например: 00000000-0000-0000-0000-000000000000");
            return false;
        }
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