package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;

public final class TaskListCommand extends Command {
    public TaskListCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-list", "Show all tasks.", Security.PRIVATE, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        for (Task task : taskService.findAllTasks(serviceLocator.getIdUserSession())) {
            Printer.showListTask(task);
        }

        System.out.println("!!!DONE!!!");
    }
}