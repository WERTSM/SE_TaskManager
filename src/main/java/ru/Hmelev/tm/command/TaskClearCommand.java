package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

public class TaskClearCommand extends Command {
    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-clear", "Remove all tasks.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceTask.clearTask();
        System.out.println("!!!DONE!!!");
    }
}