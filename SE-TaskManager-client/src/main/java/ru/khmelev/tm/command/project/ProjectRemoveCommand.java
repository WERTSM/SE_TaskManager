package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.command.Command;

import java.io.IOException;

public final class ProjectRemoveCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-remove";
    }

    @Override
    public String getDescriptionCommand() {
        return "Remove selected project";
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
        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        System.out.println("ID project: ");
        @NotNull final String id = serviceLocator.getTerminalService().readLine();

        serviceLocator.getProjectEndpoint().removeProject(session, id);
        System.out.println("!!!DONE!!!");
    }
}