package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

public final class TaskClearCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-clear";
    }

    @Override
    public String getDescriptionCommand() {
        return "Remove all tasks";
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

        serviceLocator.getTaskEndpoint().clearTask(sessionDTO);
        System.out.println("!!!DONE!!!");
    }
}