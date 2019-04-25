package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.enumeration.Sort;
import ru.khmelev.tm.util.PrinterUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskSortCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-sort";
    }

    @Override
    public String getDescriptionCommand() {
        return "Sorted tasks";
    }

    @Override
    public boolean isSecurity() {
        return false;
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

        System.out.println("Сортировать по дате создания, начала, завершения (create, start, finish) или статусу (status)?");
        Sort sortParameter = Sort.valueOf(terminalService.readLine().toUpperCase());

        @NotNull final List<TaskDTO> listTask = new ArrayList<>(serviceLocator.getTaskEndpoint().findAllTAsk(sessionDTO));

        Sort.sortTask(listTask, sortParameter);

        for (@NotNull TaskDTO taskDTO : listTask) {
            PrinterUtil.showTask(taskDTO, serviceLocator.getUserEndpoint().getUserFromSession(sessionDTO));
        }
        System.out.println("!!!DONE!!!");
    }
}