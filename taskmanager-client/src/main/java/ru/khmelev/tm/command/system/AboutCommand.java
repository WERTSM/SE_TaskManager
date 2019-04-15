package ru.khmelev.tm.command.system;

import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.command.util.Printer;

import java.io.IOException;
import java.io.InputStream;

public class AboutCommand extends Command {

    @Override
    public String getNameCommand() {
        return "about";
    }

    @Override
    public String getDescriptionCommand() {
        return "ApplicationClient Information";
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
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream inputStreamProperties = classloader.getResourceAsStream("application.properties");
        if (inputStreamProperties != null) {
            Printer.showProperties(inputStreamProperties);
        }
        if (inputStreamProperties != null) {
            inputStreamProperties.close();
        }

        Printer.showManifest();
        System.out.println("!!!DONE!!!");
    }
}