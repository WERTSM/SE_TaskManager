package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

public class TaskClearCommand extends Command {
    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-clear", "Remove all tasks.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        taskService.clearTask();
        System.out.println("!!!DONE!!!");
    }
}