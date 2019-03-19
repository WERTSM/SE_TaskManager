package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.util.UUID;

public class TaskRemoveCommand extends Command {
    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-remove", "Remove selected task.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        taskService.listTask();

        do {
            System.out.println("ID task: ");
            id = reader.readLine();
        } while (!isUUIDValid(id));
        idTask = UUID.fromString(id);

        taskService.removeTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}