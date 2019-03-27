package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;

import java.io.IOException;

public final class UserRegistryCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-registry";
    }

    @Override
    public String getDescriptionCommand() {
        return "Registers user";
    }

    @Override
    public boolean isSecurity() {
        return false;
    }

    @Override
    public Role getRoleCommand() {
        return Role.ADMIN;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("Введите логин нового пользователя: ");
        @NotNull final String login = serviceLocator.getTerminalService().readLine();
        if (login.isEmpty()) {
            return;
        }

        System.out.println("Введите пароль для нового пользователя");
        @NotNull final String password = serviceLocator.getTerminalService().readLine();
        if (password.isEmpty()) {
            return;
        }

        System.out.println("Введите тип пользователся (admin/user): ");
        @NotNull final String role = serviceLocator.getTerminalService().readLine();
        if (role.isEmpty()) {
            return;
        }

        serviceLocator.getUserService().registry(login, password, role);
        System.out.println("!!!DONE!!!");
    }
}