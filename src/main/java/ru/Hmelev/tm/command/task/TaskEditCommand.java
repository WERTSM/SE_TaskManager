package ru.Hmelev.tm.command.task;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.text.ParseException;

public class TaskEditCommand extends Command {
    public TaskEditCommand(Bootstrap bootstrap) {
        super(bootstrap, "task-edit", "Edit selected task");
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");
        taskService.listTask();

        do {
            System.out.println("ID task: ");
            idTask = reader.readLine();
        } while (!isUUIDValid(idTask));

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
            projectService.listProject();

            idProjectFromTask = reader.readLine();
            if (idProjectFromTask.equals("0")) {
                idProjectFromTask = "00000000-0000-0000-0000-000000000000";
            }
        } while (!isUUIDValid(idProjectFromTask));

        taskService.editTask(idTask, name, description, startDate, finishDate, idProjectFromTask);
        System.out.println("!!!DONE!!!");
    }
}