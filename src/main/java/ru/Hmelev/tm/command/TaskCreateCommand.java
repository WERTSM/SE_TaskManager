package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class TaskCreateCommand extends Command {
    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-create", "Create new task.");
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        do {
            System.out.println("Name task: ");
            name = reader.readLine();
        } while (!isStringValid(name));

        do {
            System.out.println("Description task: ");
            description = reader.readLine();
        } while (!isStringValid(description));

        do {
            System.out.println("Start date task: \"dd.MM.yyyy\" ");
            date = reader.readLine();
        } while (!isDateValid(date));
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        do {
            System.out.println("Finish date task: \"dd.MM.yyyy\" ");
            date = reader.readLine();
        } while (!isDateValid(date));
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        do {
            System.out.println("Id project or \'0\': ");
            serviceProject.listProject();

            id = reader.readLine();
            if (id.equals("0")) {
                id = "00000000-0000-0000-0000-000000000000";
            }
        } while (!isUUIDValid(id));
        idProjectFromTask = UUID.fromString(id);

        idTask = UUID.randomUUID();

        serviceTask.createTask(idTask, name, description, startDate, finishDate, idProjectFromTask);
        System.out.println("!!!DONE!!!");
    }
}