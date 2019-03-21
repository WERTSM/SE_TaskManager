package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public class UserLogoutCommand extends Command {
    public UserLogoutCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-logout", "descriptionCommand", Security.FREE);
    }

    @Override
    public void execute() throws IOException {

        System.out.println("!!!Start command!!!");
        bootstrap.setIdUserSession(null);
        bootstrap.setUserRoleSession(null);
    }
}