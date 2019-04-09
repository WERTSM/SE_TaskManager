package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.api.endpoint.Task;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

public final class TaskListCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-list";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show all tasks";
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
    public void execute() {
        System.out.println("!!!Start command!!!");
        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        for (Task task : serviceLocator.getTaskEndpoint().findAllTAsk(session)) {
            Printer.showListTask(task);
        }
        System.out.println("!!!DONE!!!");
    }
}