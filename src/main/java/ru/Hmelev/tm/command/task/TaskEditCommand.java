package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.text.ParseException;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public final class TaskEditCommand extends Command {
    public TaskEditCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "task-edit", "Edit selected task", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID task: ");
        idTask = reader.readLine();

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

        System.out.println("User ID: ");
        userId = reader.readLine();

        taskService.editTask(idTask, name, description, startDate, finishDate, idProjectFromTask, userId);
        System.out.println("!!!DONE!!!");
    }
}