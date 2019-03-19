package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

import java.io.IOException;
import java.util.UUID;

public class TaskRemoveCommand extends Command {
    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-remove", "Remove selected task.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        serviceTask.listTask();

        do {
            System.out.println("ID task: ");
            id = reader.readLine();
        } while (!isUUIDValid(id));
        idTask = UUID.fromString(id);

        serviceTask.removeTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}