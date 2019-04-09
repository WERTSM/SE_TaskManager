package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Project;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.command.util.SortedEntity;
import ru.khmelev.tm.dSortdddd.Sort;

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

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        System.out.println("Сортировать по дате создания, начала, завершения (create, start, finish) или статусу (status)?");
        @NotNull final Sort sortParameter = Sort.valueOf(terminalService.readLine().toUpperCase());

        @NotNull final List<Project> listProject = new ArrayList<>(serviceLocator.getProjectEndpoint().findAllProject(session));

        @NotNull final SortedEntity sortedEntity = new SortedEntity();

        sortedEntity.sort(listProject, sortParameter);

        for (Project project : listProject) {
            Printer.showProject(project, serviceLocator.getUserEndpoint().getUserFromSession(session));
        }
        System.out.println("!!!DONE!!!");
    }
}