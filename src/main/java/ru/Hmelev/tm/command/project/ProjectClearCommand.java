package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

public final class ProjectClearCommand extends Command {
    public ProjectClearCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-clear", "Remove all projects", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        user = serviceLocator.getUserSession();
        for (Project project : projectService.findAllEntities(user)) {
            taskService.removeAllTaskFromProject(project.getId(), user);
        }
        projectService.clearEntity(user);
        System.out.println("!!!DONE!!!");
    }
}