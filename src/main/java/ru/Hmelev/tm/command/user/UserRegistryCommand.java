package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public final class UserRegistryCommand extends Command {
    public UserRegistryCommand() {
        super("user-registry", "Registers user", false);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        userService = serviceLocator.getUserService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("Введите логин нового пользователя");
        login = terminalService.readLine();

        System.out.println("Введите пароль для нового пользователя");
        password = terminalService.readLine();

        System.out.println("Введите тип пользователся (admin/user)");
        role = terminalService.readLine();

        userService.registry(login, password, role);
        System.out.println("!!!DONE!!!");
    }
}