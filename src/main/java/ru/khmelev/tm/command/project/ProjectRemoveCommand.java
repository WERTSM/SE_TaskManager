package ru.khmelev.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;

import java.io.IOException;

public final class ProjectRemoveCommand extends Command {

    @Override
    public String getNameCommand() {
        return "project-remove";
    }

    @Override
    public String getDescriptionCommand() {
        return "Remove selected project";
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

        System.out.println("ID project: ");
        @NotNull final String id = serviceLocator.getTerminalService().readLine();

        serviceLocator.getTaskService().removeAllTaskFromProject(id, userId);
        serviceLocator.getProjectService().removeEntity(id, userId);
        System.out.println("!!!DONE!!!");
    }
}