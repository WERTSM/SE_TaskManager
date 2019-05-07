package ru.khmelev.tm.command.task;

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

@ApplicationScoped
public class TaskEditCommand extends Command {

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
        return "task-edit";
    }

    @Override
    public String getDescriptionCommand() {
        return "Edit selected task";
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

        System.out.println("ID task: ");
        @NotNull final String id = terminalService.readLine();
        if (id.isEmpty()) {
            return;
        }

        @NotNull final TaskDTO taskDTO = taskEndpoint.findTask(sessionDTO, id);

        System.out.println("Name task: ");
        @NotNull final String name = terminalService.readLine();
        if (name.isEmpty()) {
            return;
        }
        taskDTO.setName(name);

        System.out.println("Description task: ");
        @NotNull final String description = terminalService.readLine();
        if (description.isEmpty()) {
            return;
        }
        taskDTO.setDescription(description);

        System.out.println("Start date: \"dd.MM.yyyy\" ");
        @NotNull final String dateStartString = terminalService.readLine();

        @NotNull final Date dateStart = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateStartString));
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateFinishString));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(dateFinish));

        System.out.println("Id project or \'0\': ");
        @NotNull String projectId = terminalService.readLine();
        if (projectId.equals("0")) {
            projectId = "00000000-0000-0000-0000-000000000000";
        }
        taskDTO.setProjectId(projectId);

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        @NotNull final Status status = Status.valueOf(terminalService.readLine().toUpperCase());

        taskDTO.setStatus(status);

        taskEndpoint.editTask(sessionDTO, id, taskDTO);
        System.out.println("!!!DONE!!!");
    }
}