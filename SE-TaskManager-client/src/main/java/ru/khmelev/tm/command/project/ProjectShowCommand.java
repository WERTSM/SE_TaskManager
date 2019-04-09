package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Project;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.api.endpoint.Task;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;

public final class ProjectShowCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-show";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show selected project";
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

        @NotNull final Project project = serviceLocator.getProjectEndpoint().findProject(session, id);

        Printer.showProject(project, serviceLocator.getUserEndpoint().getUserFromSession(session));
        for (@NotNull Task task : serviceLocator.getTaskEndpoint().listTaskFromProject(session, id)) {
            Printer.showTaskInProject(task);
        }
        System.out.println("!!!DONE!!!");
    }
}