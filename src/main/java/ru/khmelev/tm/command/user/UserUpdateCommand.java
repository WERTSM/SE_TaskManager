package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;

import java.io.IOException;

public final class UserUpdateCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-update";
    }

    @Override
    public String getDescriptionCommand() {
        return "Update user";
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

        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        Printer.showUser(user);

        @NotNull final String userName = serviceLocator.getUserService().getName(user);

        System.out.println("Измените логин текущего пользователя");
        String login = serviceLocator.getTerminalService().readLine();

        System.out.println("Измените пароль текущего пользователя");
        String password = serviceLocator.getTerminalService().readLine();

        serviceLocator.getUserService().userSetPassword(userName, password);
        user.setName(login);

        System.out.println("!!!DONE!!!");
    }
}