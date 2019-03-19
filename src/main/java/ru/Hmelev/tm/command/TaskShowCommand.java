package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

import java.io.IOException;
import java.util.UUID;

public class TaskShowCommand extends Command {
    public TaskShowCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-show", "Show selected project.");
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

        serviceTask.showTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}