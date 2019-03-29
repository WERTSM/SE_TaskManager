package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.IProjectService;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.User;

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
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();

        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        @NotNull final String userId = serviceLocator.getUserService().getId(user);

        System.out.println("Выберите парамер поиска: (login, description)");
        @NotNull final String findParameter = serviceLocator.getTerminalService().readLine();

        @NotNull final Collection<Task> listTask;

        if ("login".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть имени:");
            @NotNull final String name = serviceLocator.getTerminalService().readLine();
            listTask = serviceLocator.getTaskService().findAllName(name, userId);
        } else if ("description".equalsIgnoreCase(findParameter)) {
            System.out.println("Введите часть описания:");
            @NotNull final String description = serviceLocator.getTerminalService().readLine();
            listTask = serviceLocator.getTaskService().findAllDescription(description, userId);
        } else {
            throw new IllegalArgumentException("Неправильный параметр " + findParameter);
        }

        for (Task task : listTask) {
            Printer.showTask(task, user);
        }
    }
}