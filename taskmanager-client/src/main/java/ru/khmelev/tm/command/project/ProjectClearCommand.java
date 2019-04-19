package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

public final class ProjectClearCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-clear";
    }

    @Override
    public String getDescriptionCommand() {
        return "Remove all projects";
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
        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        for (@NotNull ProjectDTO projectDTO : serviceLocator.getProjectEndpoint().findAllProject(sessionDTO)) {
            serviceLocator.getProjectEndpoint().clearProject(sessionDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}