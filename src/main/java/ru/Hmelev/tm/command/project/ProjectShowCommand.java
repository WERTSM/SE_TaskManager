package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;

public final class ProjectShowCommand extends Command {
    public ProjectShowCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-show", "Show selected project.", true, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

        user = serviceLocator.getUserSession();
        Project project = projectService.findEntity(idProject, user);

        if (project != null) {
            Printer.showProject(project, user);
            for (Task task : taskService.listTaskFromProject(idProject, user)) {
                Printer.showTaskInProject(task);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}