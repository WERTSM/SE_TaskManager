package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public class ProjectCreateCommand extends Command {
    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-create", "Create new project.");
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        System.out.println("Name project: ");
        name = reader.readLine();

        System.out.println("Description: ");
        description = reader.readLine();

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        date = reader.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        idProject = UUID.randomUUID().toString();

        projectService.createProject(idProject, name, description, startDate, finishDate);
        System.out.println("!!!DONE!!!");
    }
}