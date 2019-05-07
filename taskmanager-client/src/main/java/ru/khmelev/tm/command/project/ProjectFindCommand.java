package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

@ApplicationScoped
public class ProjectFindCommand extends Command {

    @Inject
    private
    IProjectEndpoint projectEndpoint;

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Inject
    private
    ITerminalService terminalService;

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

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        System.out.println("Выберите парамер поиска: (login, description)");
        @NotNull final String findParameter = terminalService.readLine();

        @NotNull final Collection<ProjectDTO> listProject;

        if ("login".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть имени:");
            @NotNull final String name = terminalService.readLine();
            listProject = projectEndpoint.findAllNameProject(sessionDTO, name);
        } else if ("description".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть описания:");
            @NotNull final String description = terminalService.readLine();
            listProject = projectEndpoint.findAllDescriptionProject(sessionDTO, description);
        } else {
            throw new IllegalArgumentException("Неправильный параметр " + findParameter);
        }

        for (@NotNull ProjectDTO projectDTO : listProject) {
            PrinterUtil.showProject(projectDTO, userEndpoint.getUserFromSession(sessionDTO));
        }
    }
}