package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.service.ITerminalService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.command.util.SortedEntity;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;
import ru.khmelev.tm.entity.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskSortCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-soQrt";
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

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        System.out.println("Сортировать по дате создания, начала, завершения (create, start, finish) или статусу (status)?");
        Sort sortParameter = Sort.valueOf(terminalService.readLine().toUpperCase());

        @NotNull List<Task> listTask = new ArrayList<>(serviceLocator.getTaskEndpoint().findAllTAsk(session));

        @NotNull final SortedEntity sortedEntity = new SortedEntity();

        sortedEntity.sort(listTask, sortParameter);

        for (Task task : listTask) {
            Printer.showTask(task, serviceLocator.getUserEndpoint().getUserFromSession(session));
        }
        System.out.println("!!!DONE!!!");
    }
}