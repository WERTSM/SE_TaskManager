package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

public class TaskRemoveCommand extends Command {
    public TaskRemoveCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "task-remove", "Remove selected task.");
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