package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;

public final class UserListCommand extends Command {
    public UserListCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-list", "List users", true, Role.ADMIN);
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        for (User user : userService.userList()) {
            Printer.showListUser(user);
        }
        System.out.println("!!!DONE!!!");
    }
}