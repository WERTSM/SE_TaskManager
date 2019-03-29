package ru.khmelev.tm.command.user;

import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;
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
        return false;
    }

    @Override
    public Role getRoleCommand() {
        return Role.ADMIN;
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        for (User user : serviceLocator.getUserService().findAll()) {
            Printer.showListUser(user);
        }
        System.out.println("!!!DONE!!!");
    }
}