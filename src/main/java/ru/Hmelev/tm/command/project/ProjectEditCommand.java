package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.text.ParseException;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public class ProjectEditCommand extends Command {
    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-edit", "Edit selected project", Security.PRIVATE, Role.USER);
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

        projectService.editProject(idProject, name, description, startDate, finishDate);
        System.out.println("!!!DONE!!!");
    }
}