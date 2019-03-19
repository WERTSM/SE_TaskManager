package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

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
            idTask = reader.readLine();
        } while (!isUUIDValid(idTask));

        taskService.showTask(idTask);
        System.out.println("!!!DONE!!!");
    }
}