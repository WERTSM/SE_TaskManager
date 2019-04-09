package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IAdminEndpoint {

    void serializationSave(@NotNull final Session session);

    void serializationLoad(@NotNull final Session session) throws IOException, ClassNotFoundException;

    void jaxbXmlSave(@NotNull final Session session) throws JAXBException;

    void jaxbXmlLoad(@NotNull final Session session) throws JAXBException;

    void jaxbJSONSave(@NotNull final Session session) throws JAXBException;

    void jaxbJSONLoad(@NotNull final Session session) throws JAXBException;

    void fasterXmlSaveXML(@NotNull final Session session) throws IOException;

    void fasterXmlLoadXML(@NotNull final Session session) throws IOException;

    void fasterXmlSaveJSON(@NotNull final Session session) throws IOException;

    void fasterXmlLoadJSON(@NotNull final Session session) throws IOException;
}
