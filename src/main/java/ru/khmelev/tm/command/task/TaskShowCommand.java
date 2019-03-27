package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.User;

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
        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        @NotNull final String userId = serviceLocator.getUserService().getId(user);

        System.out.println("ID task: ");
        @NotNull final String id = serviceLocator.getTerminalService().readLine();
        if (id.isEmpty()) {
            return;
        }
        @NotNull final Task task = serviceLocator.getTaskService().findEntity(id, userId);

        Printer.showTask(task, user);
        System.out.println("!!!DONE!!!");
    }
}