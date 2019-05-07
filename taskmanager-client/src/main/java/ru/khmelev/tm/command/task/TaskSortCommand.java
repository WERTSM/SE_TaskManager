package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.enumeration.Sort;
import ru.khmelev.tm.util.PrinterUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TaskSortCommand extends Command {

    @Inject
    private ITaskEndpoint taskEndpoint;

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Inject
    private
    ITerminalService terminalService;

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

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        System.out.println("Сортировать по дате создания, начала, завершения (create, start, finish) или статусу (status)?");
        Sort sortParameter = Sort.valueOf(terminalService.readLine().toUpperCase());

        @NotNull final List<TaskDTO> listTask = new ArrayList<>(taskEndpoint.findAllTAsk(sessionDTO));

        Sort.sortTask(listTask, sortParameter);

        for (@NotNull TaskDTO taskDTO : listTask) {
            PrinterUtil.showTask(taskDTO, userEndpoint.getUserFromSession(sessionDTO));
        }
        System.out.println("!!!DONE!!!");
    }
}