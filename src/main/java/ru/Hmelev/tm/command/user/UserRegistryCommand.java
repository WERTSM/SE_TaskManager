package ru.Hmelev.tm.command.user;

import org.apache.commons.lang3.EnumUtils;
import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public class UserRegistryCommand extends Command {
    public UserRegistryCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-registry", "Registers user");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Введите логин нового пользователя");
        login = reader.readLine();

        System.out.println("Введите пароль нового пользователя");
        password = reader.readLine();

        do {
            System.out.println("Введите тип пользователся (Админ/Пользователь)");
            role = reader.readLine();
        } while (EnumUtils.isValidEnum(Enum.class, role));
        userService.registry(login, password, role);
    }
}