package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;

public class TaskShowCommand extends Command {
    public TaskShowCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-show", "Show selected project.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID task: ");
        idTask = reader.readLine();

        Task task = taskService.findTask(idTask);
        Printer.showTask(task);

        System.out.println("!!!DONE!!!");
    }
}