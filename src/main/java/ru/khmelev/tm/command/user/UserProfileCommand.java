package ru.khmelev.tm.command.user;

import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;

public final class UserProfileCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-profile";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show user in system";
    }

    @Override
    public boolean isSecurity() {
        return false;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        Printer.showUser(serviceLocator.getUserSession());
        System.out.println("!!!DONE!!!");
    }
}