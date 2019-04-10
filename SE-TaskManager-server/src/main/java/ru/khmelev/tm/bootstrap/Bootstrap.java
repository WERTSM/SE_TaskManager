package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.ISaveAndLoadEndpoint;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.*;
import ru.khmelev.tm.endpoint.*;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.repository.ProjectRepository;
import ru.khmelev.tm.repository.SessionRepository;
import ru.khmelev.tm.repository.TaskRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.SessionService;
import ru.khmelev.tm.service.TaskService;
import ru.khmelev.tm.service.UserService;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class Bootstrap implements ServiceLocator {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final ISessionRepository sessionRepository = new SessionRepository();

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository);

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @Getter
    @NotNull
    private final IUserService userService = new UserService(userRepository);

    @Getter
    @NotNull
    private final ISessionService sessionService = new SessionService(sessionRepository);

    @Getter
    @NotNull
    private final IProjectEndpoint projectEndpoint = new ProjectEndpoint(sessionService, projectService);

    @Getter
    @NotNull
    private final ITaskEndpoint taskEndpoint = new TaskEndpoint(sessionService, taskService);

    @Getter
    @NotNull
    private final IUserEndpoint userEndpoint = new UserEndpoint(sessionService, userService, this);

    @Getter
    @NotNull
    private final ISaveAndLoadEndpoint saveAndLoadEndpoint = new SaveAndLoadEndpoint(projectService, taskService, userService, sessionService);


    @Getter
    @NotNull
    private final ITerminalService terminalService = new TerminalEndpoint();

    public void init() throws SQLException {

        for (Project pr : projectRepository.findAll("f1c46dbd-d06b-4784-893c-3b67b0e9da90")) {

            System.out.println(pr.getName());

        }

        System.out.println(projectRepository.findOne("4322e9aa-5660-4f8c-b43f-f75e2f7f9a72", "f1c46dbd-d06b-4784-893c-3b67b0e9da90").getName());


        /*
        Endpoint.publish("http://localhost:2019/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://localhost:2019/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://localhost:2019/UserEndpoint", userEndpoint);
        Endpoint.publish("http://localhost:2019/SaveAndLoadEndpoint", saveAndLoadEndpoint);*/
    }

}