package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

@Component
public class TaskFindCommand extends Command {

    @Autowired
    private ITaskEndpoint taskEndpoint;

    @Autowired
    private IUserEndpoint userEndpoint;

    @Autowired
    private
    ServiceLocator serviceLocator;

    @Autowired
    private
    ITerminalService terminalService;

    @Override
    public String getNameCommand() {
        return "task-find";
    }

    @Override
    public String getDescriptionCommand() {
        return "Find tasks";
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

        System.out.println("Выберите парамер поиска: (login, description)");
        @NotNull final String findParameter = terminalService.readLine();

        @NotNull final Collection<TaskDTO> listTask;

        if ("login".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть имени:");
            @NotNull final String name = terminalService.readLine();
            listTask = taskEndpoint.findAllNameTask(sessionDTO, name);
        } else if ("description".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть описания:");
            @NotNull final String description = terminalService.readLine();
            listTask = taskEndpoint.findAllDescriptionTask(sessionDTO, description);
        } else {
            throw new IllegalArgumentException("Неправильный параметр " + findParameter);
        }

        for (@NotNull TaskDTO taskDTO : listTask) {
            PrinterUtil.showTask(taskDTO, userEndpoint.getUserFromSession(sessionDTO));
        }
    }
}