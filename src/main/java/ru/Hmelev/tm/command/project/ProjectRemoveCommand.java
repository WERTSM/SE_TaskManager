package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public final class ProjectRemoveCommand extends Command {
    public ProjectRemoveCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-remove", "Remove selected project.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

        user = serviceLocator.getUserSession();
        if (user != null) {
            taskService.removeAllTaskFromProject(idProject, user);
            projectService.removeEntity(idProject, user);
        }
        System.out.println("!!!DONE!!!");
    }
}