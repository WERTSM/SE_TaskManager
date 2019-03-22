package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public final class ProjectRemoveCommand extends Command {
    public ProjectRemoveCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-remove", "Remove selected project.", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

        taskService.listTaskNoIdProject(idProject);
        projectService.removeProject(idProject);
        System.out.println("!!!DONE!!!");
    }
}