package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

public class TaskListCommand extends Command {
    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-list", "Show all tasks.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceTask.listTask();
        System.out.println("!!!DONE!!!");
    }
}