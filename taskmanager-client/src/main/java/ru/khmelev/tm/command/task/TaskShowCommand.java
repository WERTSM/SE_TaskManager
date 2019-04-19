package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;

public final class TaskShowCommand extends Command {

    @Override
    public String getNameCommand() {
        return "task-show";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show selected project";
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
        @NotNull final TaskDTO taskDTO = serviceLocator.getTaskEndpoint().findTask(sessionDTO, id);

        Printer.showTask(taskDTO, serviceLocator.getUserEndpoint().getUserFromSession(sessionDTO));
        System.out.println("!!!DONE!!!");
    }
}