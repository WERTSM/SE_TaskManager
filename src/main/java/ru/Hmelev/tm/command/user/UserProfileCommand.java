package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;

public final class UserProfileCommand extends Command {
    public UserProfileCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-profile", "Profile user", Security.FREE);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        Printer.showUser(userService.findUser(serviceLocator.getIdUserSession()));
        System.out.println("!!!DONE!!!");
    }
}