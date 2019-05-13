package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

@Component
public class TaskCreateCommand extends Command {

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private
    ServiceLocator serviceLocator;

    @Autowired
    private
    ITerminalService terminalService;

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

        taskEndpoint.createTask(sessionDTO, id, taskDTO);
        System.out.println("!!!DONE!!!");
    }
}