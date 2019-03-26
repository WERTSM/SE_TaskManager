package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public final class ProjectCreateCommand extends Command {
    public ProjectCreateCommand() {
        super("project-create", "Create new project.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        projectService = serviceLocator.getProjectService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("Name project: ");
        name = terminalService.readLine();

        System.out.println("Description: ");
        description = terminalService.readLine();

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        date = terminalService.readLine();
        startDate = DEFAULT_DATE_FORMAT.parse(date);

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        date = terminalService.readLine();
        finishDate = DEFAULT_DATE_FORMAT.parse(date);

        user = serviceLocator.getUserSession();

        if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null) {
            String id = UUID.randomUUID().toString();
            String userId = user.getId();
            Project project = new Project(id, name, description, startDate, finishDate, userId);
            projectService.createEntity(id, project);
        }
        System.out.println("!!!DONE!!!");
    }
}