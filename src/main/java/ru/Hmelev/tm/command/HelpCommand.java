package ru.Hmelev.tm.command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "Show all commands");
    }

    @Override
    public void execute() {
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
}