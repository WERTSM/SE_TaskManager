package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.ConverterUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class ProjectCreateCommand extends Command {

    @Inject
    private
    IProjectEndpoint projectEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Inject
    private
    ITerminalService terminalService;

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

        @NotNull final Date dateStart = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateStartString));
        projectDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull final String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateFinishString));
        projectDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(dateFinish));

        @NotNull final String id = UUID.randomUUID().toString();
        projectDTO.setId(id);

        projectDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));

        projectDTO.setStatus(Status.PLANNED);

        @NotNull final String userId = sessionDTO.getUserId();
        projectDTO.setUserId(userId);

        projectEndpoint.createProject(sessionDTO, id, projectDTO);
        System.out.println("!!!DONE!!!");
    }
}