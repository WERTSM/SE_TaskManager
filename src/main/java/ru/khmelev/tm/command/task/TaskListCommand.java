package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.User;

public final class TaskListCommand extends Command {

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
        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        @NotNull final String userId = serviceLocator.getUserService().getId(user);

        for (Task task : serviceLocator.getTaskService().findAll(userId)) {
            System.out.println(task);
            Printer.showListTask(task);
        }
        System.out.println("!!!DONE!!!");
    }
}