package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;

public final class TaskShowCommand extends Command {
    public TaskShowCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-show", "Show selected project.", Security.PRIVATE, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID task: ");
        idTask = reader.readLine();

        User user = userService.findUser(serviceLocator.getIdUserSession());
        Task task = taskService.findTask(idTask);

        Printer.showTask(task, user);

        System.out.println("!!!DONE!!!");
    }
}