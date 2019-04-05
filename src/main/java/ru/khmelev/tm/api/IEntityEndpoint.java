package ru.khmelev.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.entity.Session;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;

public interface IEntityEndpoint<T extends Entity> {

    void createEntity(@NotNull final Session session, @NotNull String id, @NotNull final T entity);

    T findEntity(@NotNull final Session session, @NotNull final String id);

    Collection<T> findAll(@NotNull final Session session);

    Collection<T> findAllName(@NotNull final Session session, @NotNull final String findParameter);

    Collection<T> findAllDescription(@NotNull final Session session, @NotNull final String findParameter);

    void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull final T entity);

    void removeEntity(@NotNull final Session session, @NotNull final String id);

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