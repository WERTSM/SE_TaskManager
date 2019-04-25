package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.Status;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.ConverterUtil;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public final class TaskCreateCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-create";
    }

    @Override
    public String getDescriptionCommand() {
        return "Create new task";
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

        @NotNull final TaskDTO taskDTO = new TaskDTO();

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
        taskDTO.setDateStart(ConverterUtil.convertFromDateToXMLDate((dateStart)));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull final String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Objects.requireNonNull(ConverterUtil.convertFromStringToDate(dateFinishString));
        taskDTO.setDateFinish(ConverterUtil.convertFromDateToXMLDate(dateFinish));

        @NotNull final String id = UUID.randomUUID().toString();
        taskDTO.setId(id);

        taskDTO.setDateCreate(ConverterUtil.convertFromDateToXMLDate(new Date()));

        taskDTO.setStatus(Status.PLANNED);

        @NotNull final String userId = sessionDTO.getUserId();
        taskDTO.setUserId(userId);

        System.out.println("Id project or \'0\': ");
        @NotNull String projectId = terminalService.readLine();
        if (projectId.equals("0")) {
            projectId = "00000000-0000-0000-0000-000000000000";
        }
        taskDTO.setProjectId(projectId);

        serviceLocator.getTaskEndpoint().createTask(sessionDTO, id, taskDTO);
        System.out.println("!!!DONE!!!");
    }
}