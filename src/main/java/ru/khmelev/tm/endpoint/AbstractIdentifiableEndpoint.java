package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEndpoint;
import ru.khmelev.tm.api.IService;
import ru.khmelev.tm.api.ISessionService;
import ru.khmelev.tm.entity.Identifiable;
import ru.khmelev.tm.entity.Session;

import javax.sql.rowset.serial.SerialException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;

public abstract class AbstractIdentifiableEndpoint<T extends Identifiable> implements IEndpoint<T> {

    @NotNull
    private final ISessionService sessionService;

    @NotNull
    private final IService<T> service;

    AbstractIdentifiableEndpoint(@NotNull final ISessionService sessionService, @NotNull final IService service) {
        this.service = service;
        this.sessionService = sessionService;
    }

    @Override
    public void createEntity(@NotNull final String id, @NotNull final T entity) {
        service.createEntity(id, entity);
    }

    @Override
    @NotNull
    public Collection<T> findAll(@NotNull final Session session) {
        sessionService.checkSession(session);
        return service.findAll();
    }

    @Override
    @NotNull
    public T findEntity(@NotNull final Session session, @NotNull final String id) throws SerialException {
        sessionService.checkSession(session);
        return service.findEntity(id);
    }

    @Override
    public void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull T entity) {
        sessionService.checkSession(session);
        service.editEntity(id, entity);
    }

    @Override
    public void removeEntity(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        service.removeEntity(id);
    }

    @Override
    public void clearEntity(@NotNull final Session session) {
        sessionService.checkSession(session);
        service.clearEntity();
    }

    @Override
    public void serializationSave(@NotNull final Session session) {
        sessionService.checkSession(session);
        service.serializationSave(session.getUserId());
    }

    @Override
    public void serializationLoad(@NotNull final Session session) throws IOException, ClassNotFoundException {
        sessionService.checkSession(session);
        service.serializationLoad(session.getUserId());
    }

    @Override
    public void jaxbXmlSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        service.jaxbXmlSave(session.getUserId());
    }

    @Override
    public void jaxbXmlLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        service.jaxbXmlLoad(session.getUserId());
    }

    @Override
    public void jaxbJSONSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        service.jaxbJSONSave(session.getUserId());
    }

    @Override
    public void jaxbJSONLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        service.jaxbJSONLoad(session.getUserId());
    }

    @Override
    public void fasterXmlSaveXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        service.fasterXmlSaveXML(session.getUserId());
    }

    @Override
    public void fasterXmlLoadXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        service.fasterXmlLoadXML(session.getUserId());
    }

    @Override
    public void fasterXmlSaveJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        service.fasterXmlSaveJSON(session.getUserId());
    }

    @Override
    public void fasterXmlLoadJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        service.fasterXmlLoadJSON(session.getUserId());
    }

    public abstract void userSetPassword(@NotNull Session session, @NotNull String login, @NotNull String pass);
}