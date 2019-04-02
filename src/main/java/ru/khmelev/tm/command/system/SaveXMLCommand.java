package ru.khmelev.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class SaveXMLCommand extends Command {

    @Override
    public String getNameCommand() {
        return "save-xml";
    }

    @Override
    public String getDescriptionCommand() {
        return "Save data in XML.";
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

        serviceLocator.getProjectService().jaxbXmlSave(userId);
        serviceLocator.getTaskService().jaxbXmlSave(userId);
        serviceLocator.getUserService().jaxbXmlSave(userId);
        System.out.println("!!!DONE!!!");
    }
}