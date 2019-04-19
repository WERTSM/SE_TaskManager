package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.ProjectDTO;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.Status;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public final class ProjectCreateCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-create";
    }

    @Override
    public String getDescriptionCommand() {
        return "Create new project";
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
        @NotNull final ITerminalService terminalService = serviceLocator.getTerminalService();

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final ProjectDTO projectDTO = new ProjectDTO();

        System.out.println("Name project: ");
        @NotNull final String name = terminalService.readLine();
        if (name.isEmpty()) {
            return;
        }
        projectDTO.setName(name);

        System.out.println("Description: ");
        @NotNull final String description = terminalService.readLine();
        if (description.isEmpty()) {
            return;
        }
        projectDTO.setDescription(description);

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        @NotNull final String dateStartString = terminalService.readLine();

        @NotNull final Date dateStart = Printer.parse(dateStartString);
        projectDTO.setDateStart(Printer.printXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull final String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Printer.parse(dateFinishString);
        projectDTO.setDateFinish(Printer.printXMLDate(dateFinish));

        @NotNull final String id = UUID.randomUUID().toString();
        projectDTO.setId(id);

        projectDTO.setDateCreate(Printer.printXMLDate(new Date()));

        projectDTO.setStatus(Status.PLANNED);

        @NotNull final String userId = sessionDTO.getUserId();
        projectDTO.setUserId(userId);

        serviceLocator.getProjectEndpoint().createProject(sessionDTO, id, projectDTO);
        System.out.println("!!!DONE!!!");
    }
}