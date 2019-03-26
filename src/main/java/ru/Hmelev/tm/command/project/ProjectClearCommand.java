package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;

public final class ProjectClearCommand extends Command {
    public ProjectClearCommand() {
        super("project-clear", "Remove all projects", true, Role.USER);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        projectService = serviceLocator.getProjectService();

        user = serviceLocator.getUserSession();
        if (user != null) {
            for (Project project : projectService.findAllEntities(user)) {
                taskService.removeAllTaskFromProject(project.getId(), user);
                projectService.clearEntity(user);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}