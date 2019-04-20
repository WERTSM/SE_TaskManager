package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.UserDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PasswordHashUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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
        return Role.USER;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        @NotNull final UserDTO userDTO = new UserDTO();

        @NotNull final String id = UUID.randomUUID().toString();
        userDTO.setId(id);

        System.out.println("Введите логин нового пользователя: ");
        @NotNull final String login = serviceLocator.getTerminalService().readLine();
        if (login.isEmpty()) {
            return;
        }
        userDTO.setLogin(login);

        System.out.println("Введите пароль для нового пользователя");
        @NotNull final String password = serviceLocator.getTerminalService().readLine();
        if (password.isEmpty()) {
            return;
        }
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5(password));
        userDTO.setHashPassword(hashPassword);

        System.out.println("Введите тип пользователся (admin/user): ");
        @NotNull final String roleUser = serviceLocator.getTerminalService().readLine();
        if (roleUser.isEmpty()) {
            return;
        }
        @NotNull final Role role = Role.valueOf(roleUser.toUpperCase());
        userDTO.setRole(role);

        serviceLocator.getUserEndpoint().createUser(id, userDTO);
        System.out.println("!!!DONE!!!");
    }
}