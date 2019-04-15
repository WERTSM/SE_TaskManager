package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.api.endpoint.User;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;

import java.io.IOException;
import java.util.Objects;

public final class UserUpdateCommand extends Command {

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

        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        User user = serviceLocator.getUserEndpoint().getUserFromSession(session);
        Printer.showUser(user);

        @NotNull final String userName = serviceLocator.getUserEndpoint().getName(user);

        System.out.println("Измените логин текущего пользователя");
        String login = serviceLocator.getTerminalService().readLine();
        user.setLogin(login);

        System.out.println("Измените пароль текущего пользователя");
        String password = serviceLocator.getTerminalService().readLine();
        if (password.isEmpty()) {
            return;
        }
        @NotNull final String hashPassword = Objects.requireNonNull(PasswordHashUtil.md5(password));
        user.setHashPassword(hashPassword);

        serviceLocator.getUserEndpoint().editUser(session, user.getId(), user);

        System.out.println("!!!DONE!!!");
    }
}