package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.entity.Session;

import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;

public interface IEndpoint<T extends Identifiable> {

    void createEntity(@NotNull final String id, T entity);

    @NotNull Collection<T> findAll(@NotNull final Session session);

    T findEntity(@NotNull final Session session, @NotNull String id) throws SerialException;

    void editEntity(@NotNull final Session session, @NotNull String id, T entity);

    void removeEntity(@NotNull final Session session, @NotNull String id);

    void clearEntity(@NotNull final Session session);

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
