package ru.khmelev.tm.endpoint;

import com.fasterxml.jackson.core.type.TypeReference;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IEntityEndpoint;
import ru.khmelev.tm.api.service.IEntityService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.Sort;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public abstract class AbstractEntityEndpoint<T extends Entity> implements IEntityEndpoint<T> {

    @NotNull
    private final IEntityService<T> entityService;

    @NotNull
    private final ISessionService sessionService;

    AbstractEntityEndpoint(@NotNull final ISessionService sessionService, @NotNull final IEntityService<T> entityService) {
        this.sessionService = sessionService;
        this.entityService = entityService;
    }

    protected abstract TypeReference getTypeReference();

    @Override
    public void createEntity(@NotNull final Session session, @NotNull final String id, @NotNull final T entity) {
        sessionService.checkSession(session);
        entityService.createEntity(id, entity);
    }

    @Override
    public T findEntity(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        return entityService.findEntity(id, session.getUserId());
    }

    @Override
    public Collection<T> findAll(@NotNull final Session session) {
        sessionService.checkSession(session);
        return entityService.findAll(session.getUserId());
    }

    @Override
    public Collection<T> findAllName(@NotNull final Session session, @NotNull String findParameter) {
        sessionService.checkSession(session);
        return entityService.findAllName(findParameter, session.getUserId());
    }

    @Override
    public Collection<T> findAllDescription(@NotNull final Session session, @NotNull String findParameter) {
        sessionService.checkSession(session);
        return entityService.findAllDescription(findParameter, session.getUserId());
    }

    @Override
    public void editEntity(@NotNull final Session session, @NotNull final String id, @NotNull T entity) {
        sessionService.checkSession(session);
        entityService.editEntity(id, entity, session.getUserId());
    }

    @Override
    public void removeEntity(@NotNull final Session session, @NotNull final String id) {
        sessionService.checkSession(session);
        entityService.removeEntity(id, session.getUserId());
    }

    @Override
    public void clearEntity(@NotNull final Session session) {
        sessionService.checkSession(session);
        entityService.clearEntity(session.getUserId());
    }

    @Override
    public void serializationSave(@NotNull final Session session) {
        sessionService.checkSession(session);
        entityService.serializationSave(session.getUserId());
    }

    @Override
    public void serializationLoad(@NotNull final Session session) throws IOException, ClassNotFoundException {
        sessionService.checkSession(session);
        entityService.serializationLoad(session.getUserId());
    }

    @Override
    public void jaxbXmlSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbXmlSave(session.getUserId());
    }

    @Override
    public void jaxbXmlLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbXmlLoad(session.getUserId());
    }

    @Override
    public void jaxbJSONSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbJSONSave(session.getUserId());
    }

    @Override
    public void jaxbJSONLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        entityService.jaxbJSONLoad(session.getUserId());
    }

    @Override
    public void fasterXmlSaveXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlSaveXML(session.getUserId());
    }

    @Override
    public void fasterXmlLoadXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlLoadXML(session.getUserId());
    }

    @Override
    public void fasterXmlSaveJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlSaveJSON(session.getUserId());
    }

    @Override
    public void fasterXmlLoadJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        entityService.fasterXmlLoadJSON(session.getUserId());
    }

//    @Override
//    public void soQrt(@NotNull final Session session, @NotNull final List<T> list, @NotNull final Sort sortParameter) {
//        sessionService.checkSession(session);
//        entityService.soQrt(list, sortParameter);
//    }
}