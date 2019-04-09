package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;

import java.io.IOException;
import java.util.Collection;

public class ProjectFindCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-find";
    }

    @Override
    public String getDescriptionCommand() {
        return "Find projects";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        @NotNull final IProjectEndpoint projectEndpoint = serviceLocator.getProjectEndpoint();

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        System.out.println("Выберите парамер поиска: (login, description)");
        @NotNull final String findParameter = serviceLocator.getTerminalService().readLine();

        @NotNull final Collection<Project> listProject;

        if ("login".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть имени:");
            @NotNull final String name = serviceLocator.getTerminalService().readLine();
            listProject = serviceLocator.getProjectEndpoint().findAllNameProject(session, name);
        } else if ("description".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть описания:");
            @NotNull final String description = serviceLocator.getTerminalService().readLine();
            listProject = serviceLocator.getProjectEndpoint().findAllDescriptionProject(session, description);
        } else {
            throw new IllegalArgumentException("Неправильный параметр " + findParameter);
        }

        for (Project project : listProject) {
            Printer.showProject(project, serviceLocator.getUserEndpoint().getUserFromSession(session));
        }
    }
}