package ru.Hmelev.tm.command.util;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.text.SimpleDateFormat;

public final class Printer {
    public static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static void showProject(Project project, User user) {
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

    public static void showTask(Task task, User user) {
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

    public static void showListTask(Task task) {
        if (task != null) {
            System.out.println(
                    "[ ID = " + task.getId()
                            + "; Name = " + task.getName()
                            + "; Description = " + task.getDescription()
                            + " ]");
        }
    }

    public static void showListProject(Project project) {
        if (project != null) {
            System.out.println(
                    "[ ID = " + project.getId()
                            + "; Name = " + project.getName()
                            + "; Description = " + project.getDescription()
                            + " ]");
        }
    }

    public static void showTaskInProject(Task task) {
        if (task != null) {
            System.out.println("Task in project: \n"
                    + "[ ID = " + task.getId()
                    + "; Name = " + task.getName()
                    + "; Description = " + task.getDescription()
                    + "; UserId = " + task.getUserId()
                    + " ]");
        }
    }

    public static void showListUser(User user) {
        if (user != null) {
            System.out.println(
                    "[ ID = " + user.getId()
                            + "; Login = " + user.getName()
                            + "; Role = " + user.getRole().displayName()
                            + " ]");
        }
    }

    public static void showUser(User user) {
        if (user != null) {
            System.out.println(
                    "Сейчас в системе:"
                            + "[ ID = " + user.getId()
                            + "; Login = " + user.getName()
                            + "; Role = " + user.getRole().displayName()
                            + " ]");
        }
    }
}