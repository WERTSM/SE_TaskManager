package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;

public class UserLogoutCommand extends Command {
    public UserLogoutCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-logout", "User LOGOUT", Security.FREE);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        bootstrap.setIdUserSession(null);
        bootstrap.setUserRoleSession(null);
        System.out.println("!!!DONE!!!");
    }
}