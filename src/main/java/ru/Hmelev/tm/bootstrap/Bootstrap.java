package ru.Hmelev.tm.bootstrap;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.project.*;
import ru.Hmelev.tm.command.system.ExitCommand;
import ru.Hmelev.tm.command.system.HelpCommand;
import ru.Hmelev.tm.command.task.*;
import ru.Hmelev.tm.command.user.UserLoginCommand;
import ru.Hmelev.tm.command.user.UserRegistryCommand;
import ru.Hmelev.tm.repository.ProjectsRepository;
import ru.Hmelev.tm.repository.TasksRepository;
import ru.Hmelev.tm.repository.UserRepository;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Bootstrap {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final ProjectsRepository projectsRepository = new ProjectsRepository();
    private final TasksRepository tasksRepository = new TasksRepository();
    private final UserRepository userRepository = new UserRepository();

    private final ProjectService projectService = new ProjectService(projectsRepository);
    private final TaskService taskService = new TaskService(tasksRepository);
    private final UserService userService = new UserService(userRepository);

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
            new UserLoginCommand(this)
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

    public void init() {
        for (Command command : commandArray) {
            commandMap.put(command.getNameCommand(), command);
        }

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            Command commandString;
            try {
                commandString = commandMap.get(reader.readLine());
                if (commandString != null) {
                    commandString.execute();
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("--- Command ERROR! ---");
                //e.printStackTrace();
            }
        }
    }
}