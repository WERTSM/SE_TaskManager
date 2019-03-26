package ru.Hmelev.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.InterfaceProjectService;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.repository.SuperEntityRepository;
import ru.Hmelev.tm.repository.UserRepository;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.TerminalService;
import ru.Hmelev.tm.service.UserService;

import java.util.HashMap;
import java.util.Map;

public final class Bootstrap implements ServiceLocator {
    @NotNull
    private final Map<String, Command> commandMap = new HashMap<>();

    @NotNull
    private final EntityRepository<Project> projectRepository = new SuperEntityRepository<>();
    @NotNull
    private final EntityRepository<Task> taskRepository = new SuperEntityRepository<>();
    @NotNull
    private final UserRepository userRepository = new UserRepository();

    @Getter
    @NotNull
    private final InterfaceProjectService projectService = new ProjectService(projectRepository);
    @Getter
    @NotNull
    private final InterfaceTaskService taskService = new TaskService(taskRepository);
    @Getter
    @NotNull
    private final UserService userService = new UserService(userRepository, this);
    @Getter
    @NotNull
    private final TerminalService terminalService = new TerminalService();

    @Setter
    @Getter
    @Nullable
    private User userSession;

    public void init(Class[] commandClassArray) throws IllegalAccessException, InstantiationException {
        registrationCommands(commandClassArray);
        defaultCommands();
        startCommands();
    }

    private void startCommands() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            Command command;
            try {
                String commandCmd = terminalService.readLine();
                command = commandMap.get(commandCmd);
                if (command != null) {
                    if (permitCommand(command))
                        command.execute();
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("--- Command ERROR! ---");
                e.printStackTrace();
            }
        }
    }

    private void defaultCommands() {
        userService.registry("user", "user", "user");
        userService.registry("admin", "admin", "admin");
    }

    private void registrationCommands(@NotNull Class[] commandClassArray) throws IllegalAccessException, InstantiationException {
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
        } else {
            if (commandString.getNameCommand().equals("user-login")) {
                System.out.println("Сначала выйдете из программы");
                return false;
            }
            if (userSession.getRole() == Role.ADMIN) {
                return true;
            }
            if (commandString.getRoleCommand() == userSession.getRole()) {
                return true;
            } else {
                System.out.println("Не хватает прав");
                return false;
            }
        }
    }
}