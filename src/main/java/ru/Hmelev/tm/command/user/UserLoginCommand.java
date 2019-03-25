package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public final class UserLoginCommand extends Command {
    public UserLoginCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-login", "User LOGIN", false);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        System.out.println("Введите логин пользователя");
        String login = reader.readLine();
        System.out.println("Введите пароль пользователя");
        String password = reader.readLine();
        if (userService.userLogin(login, password)) {
            System.out.println("OK");
        } else {
            System.out.println("NO OK");
        }
        System.out.println("!!!DONE!!!");
    }
}