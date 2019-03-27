package ru.khmelev.tm.command.user;

import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;

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
        return Role.ADMIN;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("Введите логин пользователя");
        String login = serviceLocator.getTerminalService().readLine();

        System.out.println("Введите пароль пользователя");
        String password = serviceLocator.getTerminalService().readLine();
        if (serviceLocator.getUserService().userLogin(login, password)) {
            System.out.println("OK");
        } else {
            System.out.println("NO OK");
        }
        System.out.println("!!!DONE!!!");
    }
}