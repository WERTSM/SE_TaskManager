package ru.khmelev.tm.command.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

@Component
public class ServerInfoCommand extends Command {

    @Autowired
    private IUserEndpoint userEndpoint;

    public String getNameCommand() {
        return "server-info";
    }

    @Override
    public String getDescriptionCommand() {
        return "Information server: HOST and PORT";
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
        System.out.println(userEndpoint.serverInfo());
        System.out.println("!!!DONE!!!");
    }
}