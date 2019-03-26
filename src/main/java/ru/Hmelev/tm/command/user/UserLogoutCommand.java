package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.command.Command;

public final class UserLogoutCommand extends Command {
    public UserLogoutCommand() {
        super("user-logout", "User LOGOUT", false);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceLocator.setUserSession(null);
        System.out.println("!!!DONE!!!");
    }
}