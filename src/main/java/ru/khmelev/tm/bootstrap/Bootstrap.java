package ru.khmelev.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.endpoint.ProjectEndpoint;
import ru.khmelev.tm.endpoint.TaskEndpoint;
import ru.khmelev.tm.endpoint.TerminalEndpoint;
import ru.khmelev.tm.endpoint.UserEndpoint;
import ru.khmelev.tm.endpoint.util.PasswordHashUtil;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.Session;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.repository.ProjectRepository;
import ru.khmelev.tm.repository.SessionRepository;
import ru.khmelev.tm.repository.TaskRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.SessionService;
import ru.khmelev.tm.service.TaskService;
import ru.khmelev.tm.service.UserService;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public final class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final SortedMap<String, Command> commandMap = new TreeMap<>();

    @Setter
    @Getter
    @Nullable
    private Session session;

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
    private final ITerminalService terminalService = new TerminalEndpoint();

    public void init(Class[] commandClassArray) throws Exception {
        registrationCommands(commandClassArray);
        defaultCommands();
        startCommands();
    }

    private void startCommands() throws IOException, SerialException {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            Command command;
            String commandCmd = terminalService.readLine();
            command = commandMap.get(commandCmd);
            if (command == null) {
                continue;
            }
            if (permitCommand(command)) {
                try {
                    command.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void defaultCommands() {
        @NotNull User user = new User();
        user.setLogin("user");
        user.setHashPassword(PasswordHashUtil.md5("user"));
        user.setRole(Role.USER);
        //@NotNull String id = UUID.randomUUID().toString();
        @NotNull String id = "11111111-1111-1111-1111-111111111111";
        user.setId(id);
        userService.createEntity(id, user);

        userEndpoint.userLogin("user", "user");

        user = new User();
        user.setLogin("admin");
        user.setHashPassword(PasswordHashUtil.md5("admin"));
        user.setRole(Role.ADMIN);
        id = UUID.randomUUID().toString();
        user.setId(id);
        userService.createEntity(id, user);
    }

    private void registrationCommands(@NotNull final Class[] commandClassArray) throws IllegalAccessException, InstantiationException {
        for (Class classCommand : commandClassArray) {
            if (classCommand.getSuperclass().equals(Command.class)) {
                Command command = (Command) classCommand.newInstance();
                command.setServiceLocator(this);
                commandMap.put(command.getNameCommand(), command);
            }
        }
    }

    private boolean permitCommand(@NotNull final Command commandString) throws SerialException {

        if (commandString.isSecurity() && session == null) {
            System.out.println("Сначала зарегистрируйтесь");
            return false;
        }

        //if (session == null) {

        //}

        if ("user-login".equals(commandString.getNameCommand()) && session != null) {
            System.out.println("Сначала выйдете из программы");
            return false;
        }

        if (session != null && userService.findEntity(session.getUserId()).getRole() == Role.ADMIN) {
            return true;
        }
        if (session != null && commandString.getRoleCommand() == userService.findEntity(session.getUserId()).getRole()) {
            return true;
        }

        if (session != null && userService.findEntity(session.getUserId()).getRole() != Role.ADMIN)
        {
            System.out.println("Не хватает прав");
            return false;
        }
        return true;
    }
}