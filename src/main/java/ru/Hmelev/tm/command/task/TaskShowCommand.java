package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;

public final class TaskShowCommand extends Command {
    public TaskShowCommand() {
        super("task-show", "Show selected project.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        taskService = serviceLocator.getTaskService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("ID task: ");
        id = terminalService.readLine();

        User user = serviceLocator.getUserSession();
        if (user != null) {
            Task task = taskService.findEntity(id, user);
            Printer.showTask(task, user);
        }
        System.out.println("!!!DONE!!!");
    }
}