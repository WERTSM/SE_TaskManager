package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ITerminalService;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.UserDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.util.PrinterUtil;

import java.io.IOException;
import java.util.Objects;

@Component
public class UserUpdateCommand extends Command {

    @Autowired
    private IUserEndpoint userEndpoint;

    @Autowired
    private
    ServiceLocator serviceLocator;

    @Autowired
    private
    ITerminalService terminalService;

    @Override
    public String getNameCommand() {
        return "user-update";
    }

    @Override
    public String getDescriptionCommand() {
        return "Update user in system";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        @NotNull final UserDTO userDTO = userEndpoint.getUserFromSession(sessionDTO);
        PrinterUtil.showUser(userDTO);

        System.out.println("Измените логин текущего пользователя");
        @NotNull final String login = terminalService.readLine();
        userDTO.setLogin(login);

        System.out.println("Измените пароль текущего пользователя");
        @NotNull final String password = terminalService.readLine();
        if (password.isEmpty()) {
            return;
        }
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5(password));
        userDTO.setHashPassword(hashPassword);

        userEndpoint.editUser(sessionDTO, userDTO.getId(), userDTO);

        System.out.println("!!!DONE!!!");
    }
}