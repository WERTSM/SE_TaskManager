package ru.khmelev.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IAdminEndpoint;
import ru.khmelev.tm.api.service.IProjectService;
import ru.khmelev.tm.api.service.ISessionService;
import ru.khmelev.tm.api.service.ITaskService;
import ru.khmelev.tm.api.service.IUserService;
import ru.khmelev.tm.entity.Session;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class AdminEndpoint implements IAdminEndpoint {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private final IUserService userService;


    @NotNull
    private final ISessionService sessionService;

    public AdminEndpoint(@NotNull final IProjectService projectService,
                         @NotNull final ITaskService taskService,
                         @NotNull final IUserService userService,
                         @NotNull final ISessionService sessionService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    public void serializationSave(@NotNull final Session session) {
        sessionService.checkSession(session);
        projectService.serializationSave(session.getUserId());
        taskService.serializationSave(session.getUserId());
        userService.serializationSave(session.getUserId());
    }

    @Override
    public void serializationLoad(@NotNull final Session session) throws IOException, ClassNotFoundException {
        sessionService.checkSession(session);
        projectService.serializationLoad(session.getUserId());
        taskService.serializationLoad(session.getUserId());
        userService.serializationLoad(session.getUserId());
    }

    @Override
    public void jaxbXmlSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        projectService.jaxbXmlSave(session.getUserId());
        taskService.jaxbXmlSave(session.getUserId());
        userService.jaxbXmlSave(session.getUserId());
    }

    @Override
    public void jaxbXmlLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        projectService.jaxbXmlLoad(session.getUserId());
        taskService.jaxbXmlLoad(session.getUserId());
        userService.jaxbXmlLoad(session.getUserId());
    }

    @Override
    public void jaxbJSONSave(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        projectService.jaxbJSONSave(session.getUserId());
        taskService.jaxbJSONSave(session.getUserId());
        userService.jaxbJSONSave(session.getUserId());
    }

    @Override
    public void jaxbJSONLoad(@NotNull final Session session) throws JAXBException {
        sessionService.checkSession(session);
        projectService.jaxbJSONLoad(session.getUserId());
        taskService.jaxbJSONLoad(session.getUserId());
        userService.jaxbJSONLoad(session.getUserId());
    }

    @Override
    public void fasterXmlSaveXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        projectService.fasterXmlSaveXML(session.getUserId());
        taskService.fasterXmlSaveXML(session.getUserId());
        userService.fasterXmlSaveXML(session.getUserId());
    }

    @Override
    public void fasterXmlLoadXML(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        projectService.fasterXmlLoadXML(session.getUserId());
        taskService.fasterXmlLoadXML(session.getUserId());
        userService.fasterXmlLoadXML(session.getUserId());
    }

    @Override
    public void fasterXmlSaveJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        projectService.fasterXmlSaveJSON(session.getUserId());
        taskService.fasterXmlSaveJSON(session.getUserId());
        userService.fasterXmlSaveJSON(session.getUserId());
    }

    @Override
    public void fasterXmlLoadJSON(@NotNull final Session session) throws IOException {
        sessionService.checkSession(session);
        projectService.fasterXmlLoadJSON(session.getUserId());
        taskService.fasterXmlLoadJSON(session.getUserId());
        userService.fasterXmlLoadJSON(session.getUserId());
    }
}