package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.text.ParseException;

public class UserUpdateCommand extends Command {
    public UserUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-update", "Update user", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException, ParseException {
        System.out.println("!!!Start command!!!");
        System.out.println("Введите логин пользователя");
        String login = reader.readLine();
        System.out.println("Введите новый пароль пользователя");
        String password = reader.readLine();
        userService.userSetPassword(login,password);
    }
}
