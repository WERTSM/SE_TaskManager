package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.text.ParseException;

import static ru.Hmelev.tm.command.util.Printer.DEFAULT_DATE_FORMAT;

public final class ProjectEditCommand extends Command {
    public ProjectEditCommand() {
        super("project-edit", "Edit selected project", true, Role.USER);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");

        projectService = serviceLocator.getProjectService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("ID project: ");
        idProject = terminalService.readLine();

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

        System.out.println("User ID: ");
        String userId = terminalService.readLine();

        user = serviceLocator.getUserSession();
        if (id != null && !id.isEmpty() && name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && finishDate != null && user != null) {
            Project project = projectService.findEntity(id, user);
            project.setName(name);
            project.setDescription(description);
            project.setDateStart(startDate);
            project.setDataFinish(finishDate);
            project.setUserId(userId);
            projectService.editEntity(idProject, project, user);
        }
        System.out.println("!!!DONE!!!");
    }
}