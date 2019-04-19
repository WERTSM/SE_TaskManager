package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import java.io.IOException;

public final class UserLoginCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-login";
    }

    @Override
    public String getDescriptionCommand() {
        return "User LOGIN";
    }

    @Override
    public boolean isSecurity() {
        return false;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("Введите логин пользователя");
        @NotNull final String login = serviceLocator.getTerminalService().readLine();

        System.out.println("Введите пароль пользователя");
        @NotNull final String password = serviceLocator.getTerminalService().readLine();

        @NotNull final SessionDTO sessionDTO = serviceLocator.getUserEndpoint().userLogin(login, password);

        if (sessionDTO != null) {
            System.out.println("OK");
            serviceLocator.setSessionDTO(sessionDTO);
        } else {
            System.out.println("NO OK");
        }
        System.out.println("!!!DONE!!!");
    }
}