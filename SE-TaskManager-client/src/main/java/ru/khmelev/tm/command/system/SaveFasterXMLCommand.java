package ru.khmelev.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.endpoint.Role;
import ru.khmelev.tm.api.endpoint.Session;
import ru.khmelev.tm.command.Command;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class SaveFasterXMLCommand extends Command {

    @Override
    public String getNameCommand() {
        return "save-fasterxml-xml";
    }

    @Override
    public String getDescriptionCommand() {
        return "Save data in FasterXML.";
    }

    @Override
    public boolean isSecurity() {
        return true;
    }

    @Override
    public Role getRoleCommand() {
        return Role.ADMIN;
    }

    @Override
    public void execute() throws IOException, JAXBException, ClassNotFoundException {
        System.out.println("!!!Start command!!!");
        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        serviceLocator.getSaveAndLoadEndpoint().fasterXmlSaveXML(session);
        System.out.println("!!!DONE!!!");
    }
}