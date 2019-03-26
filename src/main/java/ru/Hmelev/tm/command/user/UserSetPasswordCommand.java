package ru.Hmelev.tm.command.user;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;

public final class UserSetPasswordCommand extends Command {
    public UserSetPasswordCommand() {
        super("user-setPassword", "Registers user", true, Role.ADMIN);
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        userService = serviceLocator.getUserService();
        terminalService = serviceLocator.getTerminalService();

        System.out.println("Введите логин пользователя");
        String login = terminalService.readLine();

        System.out.println("Введите новый пароль пользователя");
        String password = terminalService.readLine();

        userService.userSetPassword(login, password);
        System.out.println("!!!DONE!!!");
    }
}