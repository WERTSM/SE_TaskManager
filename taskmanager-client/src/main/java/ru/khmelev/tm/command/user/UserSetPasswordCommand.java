package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class UserSetPasswordCommand extends Command {

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Inject
    private
    ITerminalService terminalService;

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
        @NotNull final String login = terminalService.readLine();
        if (login.isEmpty()) {
            return;
        }

        System.out.println("Введите новый пароль пользователя");
        @NotNull final String password = terminalService.readLine();
        if (password.isEmpty()) {
            return;
        }

        userEndpoint.userSetPassword(sessionDTO, login, password);
        System.out.println("!!!DONE!!!");
    }
}