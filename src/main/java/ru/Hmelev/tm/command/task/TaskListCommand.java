package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;

public final class TaskListCommand extends Command {
    public TaskListCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-list", "Show all tasks.", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        user = serviceLocator.getUserSession();

        for (Task task : taskService.findAllTasks(user)) {
            Printer.showListTask(task);
        }
        System.out.println("!!!DONE!!!");
    }
}