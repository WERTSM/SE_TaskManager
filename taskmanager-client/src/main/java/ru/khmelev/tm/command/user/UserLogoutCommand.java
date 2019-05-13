package ru.khmelev.tm.command.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

@Component
public class UserLogoutCommand extends Command {

    @Autowired
    private IUserEndpoint userEndpoint;

    @Autowired
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