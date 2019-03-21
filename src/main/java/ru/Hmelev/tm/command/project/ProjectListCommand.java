package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

public class ProjectListCommand extends Command {
    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-list", "Show all projects.", Security.PRIVATE, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        for (Project project : projectService.findAllProjects()) {
            Printer.showListProject(project);
        }
        System.out.println("!!!DONE!!!");
    }
}