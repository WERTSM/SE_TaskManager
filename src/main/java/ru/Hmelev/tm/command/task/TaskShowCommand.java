package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.util.UUID;

public class TaskShowCommand extends Command {
    public TaskShowCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-show", "Show selected project.");
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

        taskService.showTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}