package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.Printer;

import java.io.IOException;
import java.util.Date;

public final class TaskEditCommand extends Command {

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

        @NotNull final ITerminalService terminalService = serviceLocator.getTerminalService();

        @NotNull final ITaskEndpoint taskService = serviceLocator.getTaskEndpoint();

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("ID task: ");
        @NotNull final String id = terminalService.readLine();
        if (id.isEmpty()) {
            return;
        }

        @NotNull final TaskDTO taskDTO = serviceLocator.getTaskEndpoint().findTask(sessionDTO, id);

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

        @NotNull final Date dateStart = Printer.parse(dateStartString);
        taskDTO.setDateStart(Printer.printXMLDate(dateStart));

        System.out.println("Finish date: \"dd.MM.yyyy\" ");
        @NotNull String dateFinishString = terminalService.readLine();

        @NotNull final Date dateFinish = Printer.parse(dateFinishString);
        taskDTO.setDateFinish(Printer.printXMLDate(dateFinish));

        System.out.println("Id project or \'0\': ");
        @NotNull String projectId = terminalService.readLine();
        if (projectId.equals("0")) {
            projectId = "00000000-0000-0000-0000-000000000000";
        }
        taskDTO.setProjectId(projectId);

        System.out.println("Status: (PLANNED, INPROGRESS, DONE)");
        @NotNull final Status status = Status.valueOf(terminalService.readLine().toUpperCase());

        taskDTO.setStatus(status);

        taskService.editTask(sessionDTO, id, taskDTO);
        System.out.println("!!!DONE!!!");
    }
}