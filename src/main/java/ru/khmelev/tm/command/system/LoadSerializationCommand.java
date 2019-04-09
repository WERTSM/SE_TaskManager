package ru.khmelev.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class LoadSerializationCommand extends Command {

    @Override
    public String getNameCommand() {
        return "load-serialization";
    }

    @Override
    public String getDescriptionCommand() {
        return "Load data from bytes.";
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
        @Nullable final Session session = serviceLocator.getSession();
        if (session == null) {
            return;
        }

        @NotNull final String userId = session.getUserId();

        serviceLocator.getProjectEndpoint().serializationLoad(session);
        serviceLocator.getTaskEndpoint().serializationLoad(session);
        serviceLocator.getUserEndpoint().serializationLoad(session);
        System.out.println("!!!DONE!!!");
    }
}