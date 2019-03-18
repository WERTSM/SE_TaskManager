package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

public class TaskShowCommand extends Command {
    public TaskShowCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "task-show", "Show selected project.");
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