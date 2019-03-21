package ru.Hmelev.tm.command.util;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Task;

import java.text.SimpleDateFormat;

public class Printer {
    public static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static void showProject(Project project) {
        if (project != null) {
            System.out.println(
                    "[ ID = " + project.getId()
                            + "; Name = " + project.getName()
                            + "; Description = " + project.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(project.getDateStart())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(project.getDataFinish())
                            + " ]");
        }
    }

    public static void showTask(Task task) {
        if (task != null) {
            System.out.println(
                    "[ ID = " + task.getId()
                            + "; Name = " + task.getName()
                            + "; Description = " + task.getDescription()
                            + "; Start date = " + DEFAULT_DATE_FORMAT.format(task.getStartDate())
                            + "; Finish date = " + DEFAULT_DATE_FORMAT.format(task.getFinishDate())
                            + "; idProject = " + task.getIdProject()
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
                    + " ]");
        }
    }
}