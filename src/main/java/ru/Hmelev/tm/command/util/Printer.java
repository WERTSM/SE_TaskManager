package ru.Hmelev.tm.command.util;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

public final class Printer {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static void showProject(final Project project, final User user) {
        if (project != null) {
            System.out.println(
                    "[ ID = " + project.getId()
                            + "; Name = " + project.getName()
                            + "; Description = " + project.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(project.getDateStart())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(project.getDataFinish())
                            + "; Name user = " + user.getName()
                            + " ]");
        }
    }

    public static void showTask(final Task task, final User user) {
        if (task != null) {
            System.out.println(
                    "[ ID = " + task.getId()
                            + "; Name = " + task.getName()
                            + "; Description = " + task.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(task.getStartDate())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(task.getFinishDate())
                            + "; idProject = " + task.getIdProject()
                            + "; Name user = " + user.getName()
                            + " ]");
        }
    }

    public static void showListTask(final Task task) {
        if (task != null) {
            System.out.println(
                    "[ ID = " + task.getId()
                            + "; Name = " + task.getName()
                            + "; Description = " + task.getDescription()
                            + " ]");
        }
    }

    public static void showListProject(final Project project) {
        if (project != null) {
            System.out.println(
                    "[ ID = " + project.getId()
                            + "; Name = " + project.getName()
                            + "; Description = " + project.getDescription()
                            + " ]");
        }
    }

    public static void showTaskInProject(final Task task) {
        if (task != null) {
            System.out.println("Task in project: \n"
                    + "[ ID = " + task.getId()
                    + "; Name = " + task.getName()
                    + "; Description = " + task.getDescription()
                    + "; UserId = " + task.getUserId()
                    + " ]");
        }
    }

    public static void showListUser(final User user) {
        if (user != null) {
            System.out.println(
                    "[ ID = " + user.getId()
                            + "; Login = " + user.getName()
                            + "; Role = " + user.getRole().displayName()
                            + " ]");
        }
    }

    public static void showUser(final User user) {
        if (user != null) {
            System.out.println(
                    "Сейчас в системе:"
                            + "[ ID = " + user.getId()
                            + "; Login = " + user.getName()
                            + "; Role = " + user.getRole().displayName()
                            + " ]");
        }
    }

    public static void showProperties(final InputStream inputStreamProperties) throws IOException {
        final Properties properties = new Properties();
        properties.load(inputStreamProperties);

        final String name = properties.getProperty("application.name");
        final String version = properties.getProperty("application.version");
        final String build = properties.getProperty("application.build");

        System.out.println("Application name: " + name
                + "Application version: " + version
                + "Application build: " + build);
    }
}