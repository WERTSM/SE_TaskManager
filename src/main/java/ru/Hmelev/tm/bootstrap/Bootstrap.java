package ru.Hmelev.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.Hmelev.tm.api.EntityRepository;
import ru.Hmelev.tm.api.InterfaceProjectService;
import ru.Hmelev.tm.api.InterfaceTaskService;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.project.*;
import ru.Hmelev.tm.command.system.AboutCommand;
import ru.Hmelev.tm.command.system.ExitCommand;
import ru.Hmelev.tm.command.system.HelpCommand;
import ru.Hmelev.tm.command.task.*;
import ru.Hmelev.tm.command.user.*;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.entity.User;
import ru.Hmelev.tm.repository.SuperEntityRepository;
import ru.Hmelev.tm.repository.UserRepository;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class Bootstrap implements ServiceLocator {
    @NotNull
    private final Map<String, Command> commandMap = new HashMap<>();

    @Getter
    @NotNull
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

    @NotNull
    private final Command[] commandArray = new Command[]{
            new HelpCommand(),
            new ExitCommand(),
            new ProjectCreateCommand(this),
            new ProjectClearCommand(this),
            new ProjectListCommand(this),
            new ProjectEditCommand(this),
            new ProjectShowCommand(this),
            new ProjectRemoveCommand(this),
            new TaskCreateCommand(this),
            new TaskClearCommand(this),
            new TaskListCommand(this),
            new TaskEditCommand(this),
            new TaskShowCommand(this),
            new TaskRemoveCommand(this),
            new UserRegistryCommand(this),
            new UserLoginCommand(this),
            new UserListCommand(this),
            new UserLogoutCommand(this),
            new UserSetPasswordCommand(this),
            new UserProfileCommand(this),
            new UserUpdateCommand(this),
            new AboutCommand(this)
    };

    @Setter
    @Getter
    @Nullable
    private User userSession;

    public void init() {
        for (Command command : commandArray) {
            commandMap.put(command.getNameCommand(), command);
        }

        userService.registry("user", "user", "user");
        userService.registry("admin", "admin", "admin");

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            Command commandString;
            try {
                commandString = commandMap.get(reader.readLine());
                if (commandString != null) {
                    //System.out.println("Сейчас в системе: id_User = " + idUserSession +" Role = " + userRoleSession);
                    if (permit(commandString))
                        commandString.execute();
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("--- Command ERROR! ---");
                e.printStackTrace();
            }
        }
    }

    private boolean permit(@NotNull final Command commandString) {
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