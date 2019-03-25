package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.text.ParseException;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public final class ProjectEditCommand extends Command {
    public ProjectEditCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-edit", "Edit selected project", true, Role.USER);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

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

        System.out.println("User ID: ");
        String userId = reader.readLine();

        projectService.editProject(idProject, name, description, startDate, finishDate, userId);
        System.out.println("!!!DONE!!!");
    }
}