package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public class UserSetPasswordCommand extends Command {
    public UserSetPasswordCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-setPassword", "Registers user", Security.PRIVATE, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        System.out.println("Введите логин пользователя");
        String login = reader.readLine();
        System.out.println("Введите новый пароль пользователя");
        String password = reader.readLine();
        userService.userSetPassword(login, password);
        System.out.println("!!!DONE!!!");
    }
}