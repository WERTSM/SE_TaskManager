package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.repository.IProjectRepository;
import ru.khmelev.tm.api.repository.ISessionRepository;
import ru.khmelev.tm.api.repository.ITaskRepository;
import ru.khmelev.tm.api.repository.IUserRepository;
import ru.khmelev.tm.api.service.*;
import ru.khmelev.tm.endpoint.ProjectEndpoint;
import ru.khmelev.tm.endpoint.TaskEndpoint;
import ru.khmelev.tm.endpoint.TerminalEndpoint;
import ru.khmelev.tm.endpoint.UserEndpoint;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.repository.ProjectRepository;
import ru.khmelev.tm.repository.SessionRepository;
import ru.khmelev.tm.repository.TaskRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.SessionService;
import ru.khmelev.tm.service.TaskService;
import ru.khmelev.tm.service.UserService;

import javax.xml.ws.Endpoint;
import java.util.UUID;

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
    private final ITaskService taskService = new TaskService(taskRepository);

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskService);

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
    private final IUserEndpoint userEndpoint = new UserEndpoint(sessionService, userService);

    @Getter
    @NotNull
    private final ITerminalService terminalService = new TerminalEndpoint();

    public void init() {

        //defaultCommands();

        Endpoint.publish("http://localhost:2019/ProjectEndpoint", projectEndpoint);
        Endpoint.publish("http://localhost:2019/TaskEndpoint", taskEndpoint);
        Endpoint.publish("http://localhost:2019/UserEndpoint", userEndpoint);
    }


    private void defaultCommands() {
        @NotNull User user = new User();
        user.setLogin("user");
        user.setHashPassword(PasswordHashUtil.md5("user"));
        user.setRole(Role.USER);
        //@NotNull String id = UUID.randomUUID().toString();
        @NotNull String id = "11111111-1111-1111-1111-111111111111";
        user.setId(id);
        userEndpoint.createUser(id, user);

        user = new User();
        user.setLogin("admin");
        user.setHashPassword(PasswordHashUtil.md5("admin"));
        user.setRole(Role.ADMIN);
        id = UUID.randomUUID().toString();
        user.setId(id);
        userEndpoint.createUser(id, user);
    }
}