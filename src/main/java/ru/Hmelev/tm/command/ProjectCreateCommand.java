package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class ProjectCreateCommand extends Command {
    public ProjectCreateCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "project-create", "Create new project.");
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");
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

        idProject = UUID.randomUUID();
        serviceProject.createProject(idProject, name, description, startDate, finishDate);
        System.out.println("!!!DONE!!!");
    }
}