package ru.khmelev.tm.command.system;

import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

import javax.inject.Inject;

public class ServerInfoCommand extends Command {

    @Inject
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