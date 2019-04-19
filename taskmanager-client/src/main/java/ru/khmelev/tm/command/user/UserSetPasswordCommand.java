package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import java.io.IOException;

public final class UserSetPasswordCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-setpassword";
    }

    @Override
    public String getDescriptionCommand() {
        return "Set password user";
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

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

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

        serviceLocator.getUserEndpoint().userSetPassword(sessionDTO, login, password);
        System.out.println("!!!DONE!!!");
    }
}