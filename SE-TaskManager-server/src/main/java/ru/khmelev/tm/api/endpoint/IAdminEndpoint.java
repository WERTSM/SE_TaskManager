package ru.khmelev.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IAdminEndpoint {
    @WebMethod
    void serializationSave(@NotNull final Session session);

    @WebMethod
    void serializationLoad(@NotNull final Session session);

    @WebMethod
    void jaxbXmlSave(@NotNull final Session session);

    @WebMethod
    void jaxbXmlLoad(@NotNull final Session session);

    @WebMethod
    void jaxbJSONSave(@NotNull final Session session);

    @WebMethod
    void jaxbJSONLoad(@NotNull final Session session);

    @WebMethod
    void fasterXmlSaveXML(@NotNull final Session session);

    @WebMethod
    void fasterXmlLoadXML(@NotNull final Session session);

    @WebMethod
    void fasterXmlSaveJSON(@NotNull final Session session);

    @WebMethod
    void fasterXmlLoadJSON(@NotNull final Session session);
}
