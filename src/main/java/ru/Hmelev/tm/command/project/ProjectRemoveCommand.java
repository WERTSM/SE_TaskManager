package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public final class ProjectRemoveCommand extends Command {
    public ProjectRemoveCommand() {
        super("project-remove", "Remove selected project.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        projectService = serviceLocator.getProjectService();
        taskService = serviceLocator.getTaskService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("ID project: ");
        idProject = terminalService.readLine();

        user = serviceLocator.getUserSession();
        if (user != null) {
            taskService.removeAllTaskFromProject(idProject, user);
            projectService.removeEntity(idProject, user);
        }
        System.out.println("!!!DONE!!!");
    }
}