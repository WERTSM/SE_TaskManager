package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProjectClearCommand extends Command {

    @Inject
    private
    IProjectEndpoint projectEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

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

        projectEndpoint.clearProject(sessionDTO);
        System.out.println("!!!DONE!!!");
    }
}