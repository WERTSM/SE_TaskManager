package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import java.io.IOException;

public final class TaskRemoveCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-remove";
    }

    @Override
    public String getDescriptionCommand() {
        return "Remove selected task";
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

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("ID task: ");
        @NotNull final String id = serviceLocator.getTerminalService().readLine();
        if (id.isEmpty()) {
            return;
        }

        serviceLocator.getTaskEndpoint().removeTask(sessionDTO, id);
        System.out.println("!!!DONE!!!");
    }
}