package ru.khmelev.tm.command.user;

import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.util.PrinterUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class UserProfileCommand extends Command {

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Override
    public String getNameCommand() {
        return "user-profile";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show user in system";
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
    public void execute() {
        System.out.println("!!!Start command!!!");
        PrinterUtil.showUser(userEndpoint.getUserFromSession(Objects.requireNonNull(serviceLocator.getSessionDTO())));
        System.out.println("!!!DONE!!!");
    }
}