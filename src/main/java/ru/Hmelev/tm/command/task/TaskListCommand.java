package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;

public class TaskListCommand extends Command {
    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-list", "Show all tasks.", Security.PRIVATE, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        for (Task task : taskService.findAllTasks()) {
            Printer.showListTask(task);
        }
        taskService.findAllTasks();

        System.out.println("!!!DONE!!!");
    }
}