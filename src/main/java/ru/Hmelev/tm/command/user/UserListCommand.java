package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;

public final class UserListCommand extends Command {
    public UserListCommand() {
        super("user-list", "List users", true, Role.ADMIN);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");

        userService = serviceLocator.getUserService();

        for (User user : userService.userList()) {
            Printer.showListUser(user);
        }
        System.out.println("!!!DONE!!!");
    }
}