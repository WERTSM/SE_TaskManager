package ru.Hmelev.tm.command.system;

import ru.Hmelev.tm.bootstrap.ServiceLocator;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Role;

import java.io.IOException;
import java.io.InputStream;

public class AboutCommand extends Command {
    public AboutCommand(ServiceLocator serviceLocator) {
        super(serviceLocator, "about", "Application Information", false);
    }

    @Override
    public boolean getSecurity() {
        return super.getSecurity();
    }

    @Override
    public String getNameCommand() {
        return super.getNameCommand();
    }

    @Override
    public String getDescriptionCommand() {
        return super.getDescriptionCommand();
    }

    @Override
    public Role getRoleCommand() {
        return super.getRoleCommand();
    }

    @Override
    public void execute() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream inputStreamProperties = classloader.getResourceAsStream("application.properties");

        Printer.showProperties(inputStreamProperties);
    }
}