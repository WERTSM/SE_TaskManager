package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

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
        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        for (@NotNull TaskDTO taskDTO : serviceLocator.getTaskEndpoint().findAllTAsk(sessionDTO)) {
            PrinterUtil.showListTask(taskDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}