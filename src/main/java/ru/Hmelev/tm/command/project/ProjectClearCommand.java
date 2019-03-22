package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

public final class ProjectClearCommand extends Command {
    public ProjectClearCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-clear", "Remove all projects", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        for (Project project : projectService.findAllProjects(serviceLocator.getIdUserSession())) {
            taskService.listTaskNoIdProject(project.getId());
        }
        projectService.clearProject();
        System.out.println("!!!DONE!!!");
    }
}