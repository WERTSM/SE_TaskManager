package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Component
public class TaskListCommand extends Command {

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private
    ServiceLocator serviceLocator;

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

        for (@NotNull TaskDTO taskDTO : taskEndpoint.findAllTAsk(sessionDTO)) {
            PrinterUtil.showListTask(taskDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}