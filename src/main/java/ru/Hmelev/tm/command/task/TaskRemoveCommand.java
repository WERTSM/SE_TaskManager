package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public class TaskRemoveCommand extends Command {
    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-remove", "Remove selected task.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        taskService.findAllTasks();

        System.out.println("ID task: ");
        idTask = reader.readLine();

        taskService.removeTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}