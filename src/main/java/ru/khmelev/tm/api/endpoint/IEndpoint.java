package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;

@WebService
public interface IEndpoint<T extends Identifiable> {

    @WebMethod
    void createEntity(@NotNull final String id, @NotNull final T entity);

    @WebMethod
    @NotNull Collection<T> findAll(@NotNull final Session session);

    @WebMethod
    T findEntity(@NotNull final Session session, @NotNull String id) throws SerialException;

    @WebMethod
    void editEntity(@NotNull final Session session, @NotNull String id, T entity);

    @WebMethod
    void removeEntity(@NotNull final Session session, @NotNull String id);

    @WebMethod
    void clearEntity(@NotNull final Session session);

    @WebMethod
    void serializationSave(@NotNull final Session session);

    @WebMethod
    void serializationLoad(@NotNull final Session session) throws IOException, ClassNotFoundException;

    @WebMethod
    void jaxbXmlSave(@NotNull final Session session) throws JAXBException;

    @WebMethod
    void jaxbXmlLoad(@NotNull final Session session) throws JAXBException;

    @WebMethod
    void jaxbJSONSave(@NotNull final Session session) throws JAXBException;

    @WebMethod
    void jaxbJSONLoad(@NotNull final Session session) throws JAXBException;

    @WebMethod
    void fasterXmlSaveXML(@NotNull final Session session) throws IOException;

    @WebMethod
    void fasterXmlLoadXML(@NotNull final Session session) throws IOException;

    @WebMethod
    void fasterXmlSaveJSON(@NotNull final Session session) throws IOException;

    @WebMethod
    void fasterXmlLoadJSON(@NotNull final Session session) throws IOException;
}
