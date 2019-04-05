package ru.khmelev.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;

import java.io.IOException;

public final class TaskRemoveCommand extends Command {

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
        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        System.out.println("ID task: ");
        @NotNull final String id = serviceLocator.getTerminalService().readLine();
        if (id.isEmpty()) {
            return;
        }

        serviceLocator.getTaskEndpoint().removeEntity(session, id);
        System.out.println("!!!DONE!!!");
    }
}