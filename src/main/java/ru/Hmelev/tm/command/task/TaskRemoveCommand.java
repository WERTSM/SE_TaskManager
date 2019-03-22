package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public class TaskRemoveCommand extends Command {
    public TaskRemoveCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-remove", "Remove selected task.", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID task: ");
        idTask = reader.readLine();

        taskService.removeTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}