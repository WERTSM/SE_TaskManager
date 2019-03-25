package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;

public final class UserProfileCommand extends Command {
    public UserProfileCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-profile", "Profile user", false);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        user = serviceLocator.getUserSession();
        Printer.showUser(user);
        System.out.println("!!!DONE!!!");
    }
}