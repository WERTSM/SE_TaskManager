package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public class UserLoginCommand extends Command {
    public UserLoginCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-login", "descriptionCommand");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        System.out.println("Введите логин пользователя");
        String login = reader.readLine();
        System.out.println("Введите пароль пользователя");
    }
}