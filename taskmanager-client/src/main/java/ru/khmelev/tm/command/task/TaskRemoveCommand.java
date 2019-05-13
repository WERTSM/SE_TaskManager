package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@Component
public class TaskRemoveCommand extends Command {

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

        System.out.println("ID task: ");
        @NotNull final String id = terminalService.readLine();
        if (id.isEmpty()) {
            return;
        }

        taskEndpoint.removeTask(sessionDTO, id);
        System.out.println("!!!DONE!!!");
    }
}