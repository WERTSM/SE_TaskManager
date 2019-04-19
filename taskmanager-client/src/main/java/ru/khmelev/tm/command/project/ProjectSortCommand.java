package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.command.util.Sort;
import ru.khmelev.tm.command.util.SortedProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectSortCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-sort";
    }

    @Override
    public String getDescriptionCommand() {
        return "Sorted projects";
    }

    @Override
    public boolean isSecurity() {
        return false;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        @NotNull final ITerminalService terminalService = serviceLocator.getTerminalService();

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("Сортировать по дате создания, начала, завершения (create, start, finish) или статусу (status)?");
        @NotNull final Sort sortParameter = Sort.valueOf(terminalService.readLine().toUpperCase());

        @NotNull final List<ProjectDTO> listProject = new ArrayList<>(serviceLocator.getProjectEndpoint().findAllProject(sessionDTO));

        @NotNull final SortedProject sortedEntity = new SortedProject();

        sortedEntity.sort(listProject, sortParameter);

        for (@NotNull ProjectDTO projectDTO : listProject) {
            Printer.showProject(projectDTO, serviceLocator.getUserEndpoint().getUserFromSession(sessionDTO));
        }
        System.out.println("!!!DONE!!!");
    }
}