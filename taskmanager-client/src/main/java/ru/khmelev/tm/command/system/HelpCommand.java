package ru.khmelev.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.khmelev.tm.api.ServiceLocator;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class HelpCommand extends Command {

    @Autowired
    private
    ServiceLocator serviceLocator;

    @Override
    public String getNameCommand() {
        return "help";
    }

    @Override
    public String getDescriptionCommand() {
        return "Show all commands";
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
        @NotNull final SortedMap<String, Command> mapCommand = new TreeMap<>(serviceLocator.getCommandMap());

        System.out.println("-----------------*********** WELCOME TO TASK MANAGER ************-----------------\n");
        for (@NotNull Command command : mapCommand.values()) {
            System.out.println(command.getNameCommand() + " : " + command.getDescriptionCommand());
        }
        System.out.println("----------------- ********************************************** -----------------\n");
    }
}