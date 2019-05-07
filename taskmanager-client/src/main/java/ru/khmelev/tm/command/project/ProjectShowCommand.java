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

@ApplicationScoped
public class ProjectShowCommand extends Command {

    @Inject
    private
    IProjectEndpoint projectEndpoint;

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private ITaskEndpoint taskEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Inject
    private
    ITerminalService terminalService;

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

        System.out.println("ID project: ");
        @NotNull final String id = terminalService.readLine();

        @NotNull final ProjectDTO projectDTO = projectEndpoint.findProject(sessionDTO, id);

        PrinterUtil.showProject(projectDTO, userEndpoint.getUserFromSession(sessionDTO));

        for (@NotNull TaskDTO taskDTO : taskEndpoint.listTaskFromProject(sessionDTO, id)) {
            PrinterUtil.showTaskInProject(taskDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}