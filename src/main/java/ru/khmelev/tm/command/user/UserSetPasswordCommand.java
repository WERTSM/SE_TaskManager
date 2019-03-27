package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;

import java.io.IOException;

public final class UserSetPasswordCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-setPassword";
    }

    @Override
    public String getDescriptionCommand() {
        return "Registers user";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.ADMIN;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("Введите логин пользователя: ");
        @NotNull final String login = serviceLocator.getTerminalService().readLine();
        if (login.isEmpty()) {
            return;
        }

        System.out.println("Введите новый пароль пользователя");
        @NotNull final String password = serviceLocator.getTerminalService().readLine();
        if (password.isEmpty()) {
            return;
        }

        serviceLocator.getUserService().userSetPassword(login, password);
        System.out.println("!!!DONE!!!");
    }
}