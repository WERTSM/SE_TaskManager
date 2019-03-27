package ru.khmelev.tm.command.user;

import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;

public final class UserLogoutCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-logout";
    }

    @Override
    public String getDescriptionCommand() {
        return "User LOGOUT";
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
        serviceLocator.setUserSession(null);
        System.out.println("!!!DONE!!!");
    }
}