package ru.Hmelev.tm.command;

import com.google.common.base.Strings;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public abstract class Command {
    public BufferedReader reader;
    ServiceProject serviceProject;
    ServiceTask serviceTask;
    String name;
    String description;
    String id;
    String date;
    Date startDate;
    Date finishDate;
    UUID idProject;
    UUID idTask;
    UUID idProjectFromTask;
    SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private String nameCommand;
    private String descriptionCommand;

    public Command(String nameCommand, String descriptionCommand) {
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
    }

    public Command(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask, String nameCommand, String descriptionCommand) {
        this.reader = reader;
        this.serviceProject = serviceProject;
        this.serviceTask = serviceTask;
        this.nameCommand = nameCommand;
        this.descriptionCommand = descriptionCommand;
    }

    static boolean isStringValid(String str) {
        if (Strings.isNullOrEmpty(str)) {
            System.err.println("Введите название!");
            return false;
        } else {
            return true;
        }
    }

    boolean isDateValid(String date) {
        DEFAULT_DATE_FORMAT.setLenient(false);
        try {
            DEFAULT_DATE_FORMAT.parse(date);
            return true;
        } catch (Exception e) {
            System.err.println("Введите правильно дату. Например: 13.03.2019");
            return false;
        }
    }

    boolean isUUIDValid(String id) {
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