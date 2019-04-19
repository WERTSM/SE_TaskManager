package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
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
        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("ID project: ");
        @NotNull final String id = serviceLocator.getTerminalService().readLine();

        @NotNull final ProjectDTO projectDTO = serviceLocator.getProjectEndpoint().findProject(sessionDTO, id);

        Printer.showProject(projectDTO, serviceLocator.getUserEndpoint().getUserFromSession(sessionDTO));

        for (@NotNull TaskDTO taskDTO : serviceLocator.getTaskEndpoint().listTaskFromProject(sessionDTO, id)) {
            Printer.showTaskInProject(taskDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}