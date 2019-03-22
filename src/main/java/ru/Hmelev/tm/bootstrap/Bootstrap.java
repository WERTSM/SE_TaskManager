package ru.Hmelev.tm.bootstrap;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.project.*;
import ru.Hmelev.tm.command.system.ExitCommand;
import ru.Hmelev.tm.command.system.HelpCommand;
import ru.Hmelev.tm.command.task.*;
import ru.Hmelev.tm.command.user.*;
import ru.Hmelev.tm.command.util.Security;
import ru.Hmelev.tm.entity.Role;
import ru.Hmelev.tm.repository.*;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public final class Bootstrap implements ServiceLocator{
    private final Map<String, Command> commandMap = new HashMap<>();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final IProjectRepository projectRepository = new ProjectRepository();
    private final ITaskRepository taskRepository = new TaskRepository();
    private final IUserRepository userRepository = new UserRepository();

    private final ProjectService projectService = new ProjectService(projectRepository);
    private final TaskService taskService = new TaskService(taskRepository);
    private final UserService userService = new UserService(userRepository, this);


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
            new UserUpdateCommand(this)
    };

    public BufferedReader getReader() {
        return reader;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public UserService getUserService() {
        return userService;
    }


    private String idUserSession;
    private Role userRoleSession;

    public String getIdUserSession() {
        return idUserSession;
    }

    public void setUserRoleSession(Role userRoleSession) {
        this.userRoleSession = userRoleSession;
    }

    public void setIdUserSession(String idUserSession) {
        this.idUserSession = idUserSession;
    }

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

    private boolean permit(Command commandString) {
        if (commandString.getSecurity() == Security.FREE) {
            return true;
        }
        if (idUserSession == null) {
            System.out.println("Сначала зарегистрируйтесь");
            return false;
        } else {
            if (commandString.getNameCommand().equals("user-login")) {
                System.out.println("Сначала выйдете из программы");
                return false;
            }
            if (userRoleSession == Role.ADMIN) {
                return true;
            }
            if (commandString.getRoleCommand() == userRoleSession) {
                return true;
            } else {
                System.out.println("Не хватает прав");
                return false;
            }
        }
    }
}