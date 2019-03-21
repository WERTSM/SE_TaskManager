package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public class TaskCreateCommand extends Command {
    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-create", "Create new task.");
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        System.out.println("Name task: ");
        name = reader.readLine();

        System.out.println("Description task: ");
        description = reader.readLine();

        System.out.println("Start date task: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date task: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Id project or \'0\': ");

        idProjectFromTask = reader.readLine();
        if (idProjectFromTask.equals("0")) {
            idProjectFromTask = "00000000-0000-0000-0000-000000000000";
        }

        idTask = UUID.randomUUID().toString();

        taskService.createTask(idTask, name, description, startDate, finishDate, idProjectFromTask);
        System.out.println("!!!DONE!!!");
    }
}