package ru.Hmelev.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {
    public static final String HELP = "help";
    public static final String PROJECT_CREATE = "project-create";
    public static final String PROJECT_CLEAR = "project-clear";
    public static final String PROJECT_LIST = "project-list";
    public static final String PROJECT_EDIT = "project-edit";
    public static final String PROJECT_SHOW = "project-show";
    public static final String PROJECT_REMOVE = "project-remove";
    public static final String TASK_CREATE = "task-create";
    public static final String TASK_CLEAR = "task-clear";
    public static final String TASK_LIST = "task-list";
    public static final String TASK_EDIT = "task-edit";
    public static final String TASK_SHOW = "task-show";
    public static final String TASK_REMOVE = "task-remove";

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Command command = new Command(reader);
        String commandReader;
        while (true) {
            System.out.println("Enter the command:");
            commandReader = reader.readLine();
            switch (commandReader) {
                case HELP:
                    command.help();
                    break;
                case PROJECT_CREATE:
                    command.projectCreate();
                    break;
                case PROJECT_CLEAR:
                    command.projectClear();
                    break;
                case PROJECT_LIST:
                    command.projectList();
                    break;
                case PROJECT_EDIT:
                    command.projectEdit();
                    break;
                case PROJECT_SHOW:
                    command.projectShow();
                    break;
                case PROJECT_REMOVE:
                    command.projectRemove();
                    break;
                case TASK_CREATE:
                    command.taskCreate();
                    break;
                case TASK_CLEAR:
                    command.taskClear();
                    break;
                case TASK_LIST:
                    command.taskList();
                    break;
                case TASK_EDIT:
                    command.taskEdit();
                    break;
                case TASK_SHOW:
                    command.taskShow();
                    break;
                case TASK_REMOVE:
                    command.taskRemove();
                    break;
            }
        }
    }
}