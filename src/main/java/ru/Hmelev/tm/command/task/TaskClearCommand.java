package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

public final class TaskClearCommand extends Command {
    public TaskClearCommand() {
        super("task-clear", "Remove all tasks.", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        taskService = serviceLocator.getTaskService();

        user = serviceLocator.getUserSession();
        if (user != null) {
            taskService.clearEntity(user);
        }
        System.out.println("!!!DONE!!!");
    }
}