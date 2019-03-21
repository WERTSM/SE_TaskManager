package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;

public class UserListCommand extends Command {
    public UserListCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-list", "List users", Security.PRIVATE, Role.USER);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        for(User user: userService.userList()){
            Printer.showListUser(user);
        }
    }
}