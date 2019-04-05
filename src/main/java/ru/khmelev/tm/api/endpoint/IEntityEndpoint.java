package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebService
public interface IEntityEndpoint<T extends Entity> {
    @WebMethod
    void createEntity(@NotNull final Session session, @NotNull String id, @NotNull final T entity);

    @WebMethod
    T findEntity(@NotNull final Session session, @NotNull final String id);

    @WebMethod
    Collection<T> findAll(@NotNull final Session session);

    @WebMethod
    Collection<T> findAllName(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    Collection<T> findAllDescription(@NotNull final Session session, @NotNull final String findParameter);

    @WebMethod
    void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull final T entity);

    @WebMethod
    void removeEntity(@NotNull final Session session, @NotNull final String id);

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

    @WebMethod
    void soQrt(
            @WebParam(name = "session") @NotNull final Session session,
            @WebParam(name = "list") @NotNull final List<T> list,
            @WebParam(name = "sortParameter") @NotNull final Sort sortParameter);
}