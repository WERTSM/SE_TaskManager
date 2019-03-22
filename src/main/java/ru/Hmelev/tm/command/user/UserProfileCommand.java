package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;

public class UserProfileCommand extends Command {
    public UserProfileCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-profile", "Profile user", Security.FREE);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        Printer.showUser(userService.findUser(bootstrap.getIdUserSession()));
        System.out.println("!!!DONE!!!");
    }
}