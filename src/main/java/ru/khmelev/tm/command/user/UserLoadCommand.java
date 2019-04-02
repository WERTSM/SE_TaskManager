package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class UserLoadCommand extends Command {

    public UserLoadCommand() {
    }

    @Override
    public String getNameCommand() {
        return "user-load";
    }

    @Override
    public String getDescriptionCommand() {
        return "Load users";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.USER;
    }

    @Override
    public void execute() throws IOException, JAXBException, ClassNotFoundException {
        System.out.println("!!!Start command!!!");
        @Nullable final User user = serviceLocator.getUserSession();
        if (user == null) {
            return;
        }

        @NotNull final String userId = serviceLocator.getUserService().getId(user);

        System.out.println("Enter load method: (serialization, xml, json, fas-xml, fas-json)");
        @NotNull final String command = serviceLocator.getTerminalService().readLine();

        if ("serialization".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().serializationLoad(userId);
        } else if ("xml".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().jaxbXmlLoad(userId);
        } else if ("json".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().jaxbJSONLoad(userId);
        } else if ("fas-xml".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().fasterXmlLoadXML(userId);
        } else if ("fas-json".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().fasterXmlLoadJSON(userId);
        } else {
            throw new IllegalArgumentException("Error. No load method : " + command);
        }
        System.out.println("!!!DONE!!!");
    }
}