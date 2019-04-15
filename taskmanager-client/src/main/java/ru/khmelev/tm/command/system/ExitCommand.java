package ru.khmelev.tm.command.system;

import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;

public final class ExitCommand extends Command {

    @Override
    public String getNameCommand() {
        return "exit";
    }

    @Override
    public String getDescriptionCommand() {
        return "Exit from the program";
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
        Thread.currentThread().interrupt();
    }
}