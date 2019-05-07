package ru.khmelev.tm.command.user;

import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserLogoutCommand extends Command {

    @Inject
    private IUserEndpoint userEndpoint;

    @Inject
    private
    ServiceLocator serviceLocator;

    @Override
    public String getNameCommand() {
        return "user-logout";
    }

    @Override
    public String getDescriptionCommand() {
        return "User LOGOUT";
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
        userEndpoint.userLogOut(serviceLocator.getSessionDTO());
        serviceLocator.setSessionDTO(null);
        System.out.println("!!!DONE!!!");
    }
}