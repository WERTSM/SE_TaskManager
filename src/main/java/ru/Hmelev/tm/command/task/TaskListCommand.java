package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

public class TaskListCommand extends Command {
    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-list", "Show all tasks.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        taskService.listTask();
        System.out.println("!!!DONE!!!");
    }
}