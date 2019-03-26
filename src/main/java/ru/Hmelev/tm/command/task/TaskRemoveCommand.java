package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public final class TaskRemoveCommand extends Command {
    public TaskRemoveCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-remove", "Remove selected task.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID task: ");
        idTask = reader.readLine();

        user = serviceLocator.getUserSession();
        if (user != null) {
            taskService.removeEntity(idTask, user);
        }
        System.out.println("!!!DONE!!!");
    }
}