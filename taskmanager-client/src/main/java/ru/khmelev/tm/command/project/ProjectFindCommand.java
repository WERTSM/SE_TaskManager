package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

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

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("Выберите парамер поиска: (login, description)");
        @NotNull final String findParameter = serviceLocator.getTerminalService().readLine();

        @NotNull final Collection<ProjectDTO> listProject;

        if ("login".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть имени:");
            @NotNull final String name = serviceLocator.getTerminalService().readLine();
            listProject = serviceLocator.getProjectEndpoint().findAllNameProject(sessionDTO, name);
        } else if ("description".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть описания:");
            @NotNull final String description = serviceLocator.getTerminalService().readLine();
            listProject = serviceLocator.getProjectEndpoint().findAllDescriptionProject(sessionDTO, description);
        } else {
            throw new IllegalArgumentException("Неправильный параметр " + findParameter);
        }

        for (@NotNull ProjectDTO projectDTO : listProject) {
            Printer.showProject(projectDTO, serviceLocator.getUserEndpoint().getUserFromSession(sessionDTO));
        }
    }
}