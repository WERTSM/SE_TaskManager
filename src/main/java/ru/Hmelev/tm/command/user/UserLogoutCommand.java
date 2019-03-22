package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;

public class UserLogoutCommand extends Command {
    public UserLogoutCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-logout", "User LOGOUT", Security.FREE);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceLocator.setIdUserSession(null);
        serviceLocator.setUserRoleSession(null);
        System.out.println("!!!DONE!!!");
    }
}