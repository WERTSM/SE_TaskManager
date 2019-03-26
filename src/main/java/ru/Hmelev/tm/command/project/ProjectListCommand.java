package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

public final class ProjectListCommand extends Command {
    public ProjectListCommand() {
        super("project-list", "Show all projects.", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        projectService = serviceLocator.getProjectService();

        user = serviceLocator.getUserSession();
        if (user != null) {
            for (Project project : projectService.findAllEntities(user)) {
                Printer.showListProject(project);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}