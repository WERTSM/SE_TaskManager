package ru.Hmelev.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {
    private static final String HELP = "help";
    private static final String PROJECT_CREATE = "project-create";
    private static final String PROJECT_CLEAR = "project-clear";
    private static final String PROJECT_LIST = "project-list";
    private static final String PROJECT_EDIT = "project-edit";
    private static final String PROJECT_SHOW = "project-show";
    private static final String PROJECT_REMOVE = "project-remove";
    private static final String TASK_CREATE = "task-create";
    private static final String TASK_CLEAR = "task-clear";
    private static final String TASK_LIST = "task-list";
    private static final String TASK_EDIT = "task-edit";
    private static final String TASK_SHOW = "task-show";
    private static final String TASK_REMOVE = "task-remove";
    private static final String EXIT = "exit";


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
                case EXIT:
                    command.exit();
                    break;
            }
        }
    }
}