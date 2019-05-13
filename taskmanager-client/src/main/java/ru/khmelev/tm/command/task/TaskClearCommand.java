package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Component
public class TaskClearCommand extends Command {

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private
    ServiceLocator serviceLocator;

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

        taskEndpoint.clearTask(sessionDTO);
        System.out.println("!!!DONE!!!");
    }
}