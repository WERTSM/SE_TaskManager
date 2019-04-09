package ru.khmelev.tm.command.user;

import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

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
        return Role.USER;
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceLocator.getUserEndpoint().userLogOut(serviceLocator.getSession());
        serviceLocator.setSession(null);
        System.out.println("!!!DONE!!!");
    }
}