package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Project;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
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
        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        for (Project project : serviceLocator.getProjectEndpoint().findAllProject(session)) {
            serviceLocator.getTaskEndpoint().removeAllTaskFromProject(session, project.getId());
            serviceLocator.getProjectEndpoint().clearProject(session);
        }
        System.out.println("!!!DONE!!!");
    }
}