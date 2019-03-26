package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;

public final class TaskListCommand extends Command {
    public TaskListCommand() {
        super("task-list", "Show all tasks.", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        taskService = serviceLocator.getTaskService();

        user = serviceLocator.getUserSession();
        if (user != null) {
            for (Task task : taskService.findAllEntities(user)) {
                System.out.println(task);
                Printer.showListTask(task);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}