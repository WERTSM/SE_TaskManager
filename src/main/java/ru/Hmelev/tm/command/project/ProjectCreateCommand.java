package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class ProjectCreateCommand extends Command {
    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-create", "Create new project.");
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

        idProject = UUID.randomUUID().toString();
        projectService.createProject(idProject, name, description, startDate, finishDate);
        System.out.println("!!!DONE!!!");
    }
}