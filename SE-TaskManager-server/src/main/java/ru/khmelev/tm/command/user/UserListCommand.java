package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;

public final class UserListCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-list";
    }

    @Override
    public String getDescriptionCommand() {
        return "List users";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.ADMIN;
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        for (User userok : serviceLocator.getUserEndpoint().findAll(session)) {
            Printer.showListUser(userok);
        }
        System.out.println("!!!DONE!!!");
    }
}