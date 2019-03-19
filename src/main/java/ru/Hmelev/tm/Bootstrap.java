package ru.Hmelev.tm;

import ru.Hmelev.tm.command.*;
import ru.Hmelev.tm.repository.ProjectsRepository;
import ru.Hmelev.tm.repository.TasksRepository;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

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

    private ServiceProject serviceProject = new ServiceProject(projectsRepository);
    private ServiceTask serviceTask = new ServiceTask(tasksRepository);

    public BufferedReader getReader() {
        return reader;
    }

    public ServiceProject getServiceProject() {
        return serviceProject;
    }

    public ServiceTask getServiceTask() {
        return serviceTask;
    }

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
            new TaskRemoveCommand(this)
    };

    void init() throws IOException, ParseException {
        for (Command commands : command) {
            commandMap.put(commands.getNameCommand(), commands);
        }
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            String str = reader.readLine();
            for (Map.Entry<String, Command> item : commandMap.entrySet()) {
                if (str.equals(item.getKey())) {
                    item.getValue().execute();
                }
            }
        }
    }
}