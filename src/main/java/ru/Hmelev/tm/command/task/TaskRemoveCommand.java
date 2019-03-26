package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public final class TaskRemoveCommand extends Command {
    public TaskRemoveCommand() {
        super("task-remove", "Remove selected task.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        taskService = serviceLocator.getTaskService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("ID task: ");
        idTask = terminalService.readLine();

        user = serviceLocator.getUserSession();
        if (user != null) {
            taskService.removeEntity(idTask, user);
        }
        System.out.println("!!!DONE!!!");
    }
}