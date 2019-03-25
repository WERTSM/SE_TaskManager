package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

public final class TaskClearCommand extends Command {
    public TaskClearCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-clear", "Remove all tasks.", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        user = serviceLocator.getUserSession();
        taskService.clearEntity(user);
        System.out.println("!!!DONE!!!");
    }
}