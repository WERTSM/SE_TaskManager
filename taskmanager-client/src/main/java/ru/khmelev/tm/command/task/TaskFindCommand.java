package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.TaskDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;
import java.util.Collection;

public class TaskFindCommand extends Command {

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
        @NotNull final IProjectEndpoint projectEndpoint = serviceLocator.getProjectEndpoint();

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final String userId = sessionDTO.getUserId();

        System.out.println("Выберите парамер поиска: (login, description)");
        @NotNull final String findParameter = serviceLocator.getTerminalService().readLine();

        @NotNull final Collection<TaskDTO> listTask;

        if ("login".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть имени:");
            @NotNull final String name = serviceLocator.getTerminalService().readLine();
            listTask = serviceLocator.getTaskEndpoint().findAllNameTask(sessionDTO, name);
        } else if ("description".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть описания:");
            @NotNull final String description = serviceLocator.getTerminalService().readLine();
            listTask = serviceLocator.getTaskEndpoint().findAllDescriptionTask(sessionDTO, description);
        } else {
            throw new IllegalArgumentException("Неправильный параметр " + findParameter);
        }

        for (@NotNull TaskDTO taskDTO : listTask) {
            Printer.showTask(taskDTO, serviceLocator.getUserEndpoint().getUserFromSession(sessionDTO));
        }
    }
}