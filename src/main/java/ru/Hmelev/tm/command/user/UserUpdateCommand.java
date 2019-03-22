package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.User;

import java.io.IOException;

public class UserUpdateCommand extends Command {
    public UserUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-update", "Update user", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        User user = userService.findUser(bootstrap.getIdUserSession());
        Printer.showUser(user);

        System.out.println("Измените логин текущего пользователя");
        String login = reader.readLine();

        System.out.println("Измените пароль текущего пользователя");
        String password = reader.readLine();


        userService.userSetPassword(user.getName(), password);
        user.setName(login);
        System.out.println("!!!DONE!!!");
    }
}
