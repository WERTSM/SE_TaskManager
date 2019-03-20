package ru.Hmelev.tm;

import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.ExitCommand;
import ru.Hmelev.tm.command.HelpCommand;
import ru.Hmelev.tm.command.project.*;
import ru.Hmelev.tm.command.task.*;
import ru.Hmelev.tm.command.user.UserRegistryCommand;
import ru.Hmelev.tm.repository.ProjectsRepository;
import ru.Hmelev.tm.repository.TasksRepository;
import ru.Hmelev.tm.repository.UserRepository;
import ru.Hmelev.tm.service.ProjectService;
import ru.Hmelev.tm.service.TaskService;
import ru.Hmelev.tm.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Bootstrap {
    private Map<String, Command> commandMap = new HashMap<>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private ProjectsRepository projectsRepository = new ProjectsRepository();
    private TasksRepository tasksRepository = new TasksRepository();
    private UserRepository userRepository = new UserRepository();

    private ProjectService projectService = new ProjectService(projectsRepository);
    private TaskService taskService = new TaskService(tasksRepository);
    private UserService userService = new UserService(userRepository);

    private Command command[] = new Command[]{
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
            new UserRegistryCommand(this)
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

    void init() throws IOException, ParseException {
        for (Command commands : command) {
            commandMap.put(commands.getNameCommand(), commands);
        }

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            Command commandString = commandMap.get(reader.readLine());
            if (commandString != null) {
                commandString.execute();
            }
        }
    }
}