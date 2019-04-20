package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.khmelev.tm.api.endpoint.IProjectEndpoint;
import ru.khmelev.tm.api.endpoint.ITaskEndpoint;
import ru.khmelev.tm.api.endpoint.IUserEndpoint;
import ru.khmelev.tm.api.service.*;
import ru.khmelev.tm.endpoint.ProjectEndpoint;
import ru.khmelev.tm.endpoint.TaskEndpoint;
import ru.khmelev.tm.endpoint.TerminalEndpoint;
import ru.khmelev.tm.endpoint.UserEndpoint;
import ru.khmelev.tm.util.PasswordHashUtil;
import ru.khmelev.tm.dto.UserDTO;
import ru.khmelev.tm.enumeration.Role;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.SessionService;
import ru.khmelev.tm.service.TaskService;
import ru.khmelev.tm.service.UserService;

import javax.xml.ws.Endpoint;
import java.util.UUID;

public final class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService();

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService();

    @Getter
    @NotNull
    private final IUserService userService = new UserService();

    @Getter
    @NotNull
    private final ISessionService sessionService = new SessionService();

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
        @NotNull UserDTO userDTO = new UserDTO();
        userDTO.setLogin("user");
        userDTO.setRole(Role.USER);
        //@NotNull String id = UUID.randomUUID().toString();
        @NotNull String id = "11111111-1111-1111-1111-111111111111";
        userDTO.setId(id);
        userEndpoint.createUser(id, userDTO);

        userDTO = new UserDTO();
        userDTO.setLogin("admin");
        userDTO.setHashPassword(PasswordHashUtil.md5("admin"));
        userDTO.setRole(Role.ADMIN);
        id = UUID.randomUUID().toString();
        userDTO.setId(id);
        userEndpoint.createUser(id, userDTO);
    }
}