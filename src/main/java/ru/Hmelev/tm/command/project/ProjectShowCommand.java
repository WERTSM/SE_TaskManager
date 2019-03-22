package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;

public final class ProjectShowCommand extends Command {
    public ProjectShowCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "project-show", "Show selected project.", Security.PRIVATE, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

        User user = userService.findUser(serviceLocator.getIdUserSession());
        Project project = projectService.findProject(idProject);
        if (project != null) {
            Printer.showProject(project, user);
            for (Task task : taskService.listTaskIdProject(idProject)) {
                Printer.showTaskInProject(task);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}