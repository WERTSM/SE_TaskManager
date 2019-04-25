package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

public final class ProjectListCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-list";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show all projects";
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
    public void execute() {
        System.out.println("!!!Start command!!!");
        @NotNull final IProjectEndpoint projectEndpoint = serviceLocator.getProjectEndpoint();

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        for (@NotNull ProjectDTO projectDTO : projectEndpoint.findAllProject(sessionDTO)) {
            PrinterUtil.showListProject(projectDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}