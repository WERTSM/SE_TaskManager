package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class ProjectEditCommand extends Command {
    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-edit", "Edit selected project");
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();

        do {
            System.out.println("ID project: ");
            id = reader.readLine();
        } while (!isUUIDValid(id));
        idProject = UUID.fromString(id);

        do {
            System.out.println("Name project: ");
            name = reader.readLine();
        } while (!isStringValid(name));

        do {
            System.out.println("Description: ");
            description = reader.readLine();
        } while (!isStringValid(description));

        do {
            System.out.println("Start date: \"dd.MM.yyyy\" ");
            date = reader.readLine();
        } while (!isDateValid(date));
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        do {
            System.out.println("Finish date: \"dd.MM.yyyy\" ");
            date = reader.readLine();
        } while (!isDateValid(date));
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        serviceProject.editProject(idProject, name, description, startDate, finishDate);
        System.out.println("!!!DONE!!!");
    }
}