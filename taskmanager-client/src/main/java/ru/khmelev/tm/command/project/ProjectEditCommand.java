package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.ConverterUtil;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public final class ProjectEditCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-edit";
    }

    @Override
    public String getDescriptionCommand() {
        return "Edit selected project";
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

        @NotNull final IProjectEndpoint projectEndpoint = serviceLocator.getProjectEndpoint();

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("ID project: ");
        @NotNull final String id = terminalService.readLine();
        if (id.isEmpty()) {
            return;
        }

        @NotNull final ProjectDTO projectDTO = projectEndpoint.findProject(sessionDTO, id);

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

        @NotNull final Date dateStart = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateStartString));
        projectDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateFinishString));
        projectDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(dateFinish));

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        @NotNull final Status status = Status.valueOf(terminalService.readLine().toUpperCase());
        projectDTO.setStatus(status);

        projectDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));

        projectEndpoint.editProject(sessionDTO, id, projectDTO);
        System.out.println("!!!DONE!!!");
    }
}