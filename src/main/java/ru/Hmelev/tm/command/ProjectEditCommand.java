package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class ProjectEditCommand extends Command {

    public ProjectEditCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "project-edit", "Edit selected project");
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
        name = reader.readLine();

        do {
            System.out.println("Description: ");
            description = reader.readLine();
        } while (!isStringValid(description));
        description = reader.readLine();

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