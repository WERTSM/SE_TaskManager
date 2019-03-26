package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public final class TaskCreateCommand extends Command {
    public TaskCreateCommand() {
        super("task-create", "Create new task.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        taskService = serviceLocator.getTaskService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("Name task: ");
        name = terminalService.readLine();

        System.out.println("Description task: ");
        description = terminalService.readLine();

        System.out.println("Start date task: \"dd.MM.yyyy\" ");
        date = terminalService.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date task: \"dd.MM.yyyy\" ");
        date = terminalService.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Id project or \'0\': ");

        idProjectFromTask = terminalService.readLine();
        if (idProjectFromTask.equals("0")) {
            idProjectFromTask = "00000000-0000-0000-0000-000000000000";
        }

        user = serviceLocator.getUserSession();

        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null && idProjectFromTask != null && !idProjectFromTask.isEmpty() && user != null) {
            String id = UUID.randomUUID().toString();
            String userId = user.getId();
            Task task = new Task(id, name, description, startDate, finishDate, idProject, userId);
            taskService.createEntity(id, task);
        }
        System.out.println("!!!DONE!!!");
    }
}