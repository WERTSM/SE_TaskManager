package ru.khmelev.tm.bootstrap;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.khmelev.tm.api.*;
import ru.khmelev.tm.command.Command;
import ru.khmelev.tm.entity.Role;
import ru.khmelev.tm.entity.User;
import ru.khmelev.tm.repository.ProjectRepository;
import ru.khmelev.tm.repository.TaskRepository;
import ru.khmelev.tm.repository.UserRepository;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.TaskService;
import ru.khmelev.tm.service.TerminalService;
import ru.khmelev.tm.service.UserService;

import java.io.IOException;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class Bootstrap implements ServiceLocator {

    @Getter
    @NotNull
    private final SortedMap<String, Command> commandMap = new TreeMap<>();

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository);

    @Getter
    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @Getter
    @NotNull
    private final IUserService userService = new UserService(userRepository, this);

    @Getter
    @NotNull
    private final ITerminalService terminalService = new TerminalService();

    @Setter
    @Getter
    @Nullable
    private User userSession;

    public void init(Class[] commandClassArray) throws Exception {
        registrationCommands(commandClassArray);
        defaultCommands();
        startCommands();
    }

    private void startCommands() throws IOException {
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
        user.setHashPassword(Hashing.sha256().hashString("user", UTF_8).toString());
        user.setRole(Role.USER);
        //@NotNull String id = UUID.randomUUID().toString();
        @NotNull String id = "11111111-1111-1111-1111-111111111111";
        user.setId(id);
        userService.createEntity(id, user);

        setUserSession(user);

        user = new User();
        user.setLogin("admin");
        user.setHashPassword(Hashing.sha256().hashString("admin", UTF_8).toString());
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

    private boolean permitCommand(@NotNull final Command commandString) {
        if (!commandString.isSecurity()) {
            return true;
        }
        if (userSession == null) {
            System.out.println("Сначала зарегистрируйтесь");
            return false;
        }
        if (commandString.getNameCommand().equals("user-login")) {
            System.out.println("Сначала выйдете из программы");
            return false;
        }
        if (userSession.getRole() == Role.ADMIN) {
            return true;
        }
        if (commandString.getRoleCommand() == userSession.getRole()) {
            return true;
        }
        System.out.println("Не хватает прав");
        return false;
    }
}