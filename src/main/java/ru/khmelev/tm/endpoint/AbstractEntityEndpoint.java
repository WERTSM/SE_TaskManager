package ru.khmelev.tm.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.IEntityEndpoint;
import ru.khmelev.tm.api.IEntityService;
import ru.khmelev.tm.api.ISessionService;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.entity.Session;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;

public abstract class AbstractEntityEndpoint<T extends Entity> implements IEntityEndpoint<T> {

    private IEntityService<T> entityService;
    private ISessionService sessionService;

    AbstractEntityEndpoint(final ISessionService sessionService, final IEntityService<T> entityService) {
        this.sessionService = sessionService;
        this.entityService = entityService;
    }

    protected abstract TypeReference getTypeReference();

    public void createEntity(@NotNull final Session session, final String id, @NotNull final T entity) {
        sessionService.checkSession(session);
        entityService.createEntity(id, entity);
    }

    @NotNull
    @Override
    public T findEntity(@NotNull final Session session, @NotNull final String id, @NotNull final String userId) {
        sessionService.checkSession(session);
        return entityService.findEntity(id, session.getUserId());
    }

    @NotNull
    @Override
    public Collection<T> findAll(@NotNull final Session session, @NotNull final String userId) {
        sessionService.checkSession(session);
        return entityService.findAll(session.getUserId());
    }

    @NotNull
    @Override
    public Collection<T> findAllName(@NotNull final Session session, String findParameter, String userId) {
        sessionService.checkSession(session);
        return entityService.findAllName(findParameter, session.getUserId());
    }

    @NotNull
    @Override
    public Collection<T> findAllDescription(@NotNull final Session session, String findParameter, String userId) {
        sessionService.checkSession(session);
        return entityService.findAllDescription(findParameter, session.getUserId());
    }

    @Override
    public void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull T entity, @NotNull final String userId) {
        sessionService.checkSession(session);
        entityService.editEntity(id, entity, session.getUserId());
    }

    @Override
    public void removeEntity(@NotNull final Session session, @NotNull final String id, @NotNull final String userId) {
        sessionService.checkSession(session);
        entityService.removeEntity(id, session.getUserId());
    }

    @Override
    public void clearEntity(@NotNull final Session session, @NotNull final String userId) {
        sessionService.checkSession(session);
        entityService.clearEntity(session.getUserId());
    }


    public void serializationSave(@NotNull final Session session) {
        sessionService.checkSession(session);
        entityService.serializationSave(session.getUserId());
    }


    public void serializationLoad(@NotNull final Session session) throws IOException, ClassNotFoundException {
        sessionService.checkSession(session);
        entityService.serializationLoad(session.getUserId());
    }

    public void jaxbXmlSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbXmlSave(session.getUserId());
    }


    public void jaxbXmlLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbXmlLoad(session.getUserId());
    }

    public void jaxbJSONSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbJSONSave(session.getUserId());
    }

    public void jaxbJSONLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbJSONLoad(session.getUserId());
    }

    public void fasterXmlSaveXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlSaveXML(session.getUserId());
    }

    public void fasterXmlLoadXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlLoadXML(session.getUserId());
    }

    public void fasterXmlSaveJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlSaveJSON(session.getUserId());
    }

    public void fasterXmlLoadJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlLoadJSON(session.getUserId());
    }
}