package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.SessionDTO;
import ru.khmelev.tm.api.endpoint.UserDTO;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

public final class UserListCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-list";
    }

    @Override
    public String getDescriptionCommand() {
        return "List users";
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
    public void execute() {
        System.out.println("!!!Start command!!!");

        @Nullable final SessionDTO sessionDTO = serviceLocator.getSessionDTO();
        if (sessionDTO == null) {
            return;
        }

        for (@NotNull UserDTO userDTO : serviceLocator.getUserEndpoint().findAllUser(sessionDTO)) {
            PrinterUtil.showListUser(userDTO);
        }
        System.out.println("!!!DONE!!!");
    }
}