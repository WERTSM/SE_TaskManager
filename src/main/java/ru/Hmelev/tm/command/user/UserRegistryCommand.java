package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Security;

import java.io.IOException;

public class UserRegistryCommand extends Command {
    public UserRegistryCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "user-registry", "Registers user", Security.FREE);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("Введите логин нового пользователя");
        login = reader.readLine();

        System.out.println("Введите пароль для нового пользователя");
        password = reader.readLine();

        System.out.println("Введите тип пользователся (Админ/Пользователь)");
        role = reader.readLine();

        userService.registry(login, password, role);
        System.out.println("!!!DONE!!!");
    }
}