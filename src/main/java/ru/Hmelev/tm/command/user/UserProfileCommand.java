package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;

public final class UserProfileCommand extends Command {
    public UserProfileCommand() {
        super("user-profile", "Profile user", false);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        userService = serviceLocator.getUserService();

        user = serviceLocator.getUserSession();
        Printer.showUser(user);
        System.out.println("!!!DONE!!!");
    }
}