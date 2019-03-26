package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;

public final class UserUpdateCommand extends Command {
    public UserUpdateCommand() {
        super("user-update", "Update user", true, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        userService = serviceLocator.getUserService();
        terminalService = serviceLocator.getTerminalService();

        User user = serviceLocator.getUserSession();
        Printer.showUser(user);

        System.out.println("Измените логин текущего пользователя");
        String login = terminalService.readLine();

        System.out.println("Измените пароль текущего пользователя");
        String password = terminalService.readLine();

        if (user != null) {
            userService.userSetPassword(user.getName(), password);
            user.setName(login);
        }
        System.out.println("!!!DONE!!!");
    }
}