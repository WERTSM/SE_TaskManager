package ru.khmelev.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class UserSaveCommand extends Command {

    @Override
    public String getNameCommand() {
        return "user-save";
    }

    @Override
    public String getDescriptionCommand() {
        return "Save users";
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

        System.out.println("Enter save method: (serialization, xml, json, fas-xml, fas-json)");
        @NotNull final String command = serviceLocator.getTerminalService().readLine();

        if ("serialization".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().serializationSave(userId);
        } else if ("xml".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().jaxbXmlSave(userId);
        } else if ("json".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().jaxbJSONSave(userId);
        } else if ("fas-xml".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().fasterXmlSaveXML(userId);
        } else if ("fas-json".equalsIgnoreCase(command)) {
            serviceLocator.getUserService().fasterXmlSaveJSON(userId);
        } else {
            throw new IllegalArgumentException("Error. No save method : " + command);
        }
        System.out.println("!!!DONE!!!");
    }
}