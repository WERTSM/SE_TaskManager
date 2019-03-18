package ru.Hmelev.tm;

import ru.Hmelev.tm.command.*;
import ru.Hmelev.tm.repository.ProjectsRepository;
import ru.Hmelev.tm.repository.TasksRepository;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Bootstrap {

    private Map<String, Command> commandMap = new HashMap<>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    ProjectsRepository projectsRepository = ProjectsRepository.getInstance();
    TasksRepository tasksRepository = TasksRepository.getInstance();

    ServiceProject serviceProject = new ServiceProject(projectsRepository);
    ServiceTask serviceTask = new ServiceTask (tasksRepository);

    String str;

    Command command[] = new Command[]{
            new HelpCommand(),
            new ExitCommand(),
            new ProjectCreateCommand(),
            new ProjectClearCommand(),
            new ProjectListCommand(),
            new ProjectEditCommand(),
            new ProjectShowCommand(),
            new ProjectRemoveCommand(),
            new TaskCreateCommand(),
            new TaskClearCommand(),
            new TaskListCommand(),
            new TaskEditCommand(),
            new TaskShowCommand(),
            new TaskRemoveCommand()
    };

    public void init() throws IOException {
        for (Command commands : command) {
            commandMap.put(commands.getName(), commands);
        }
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enter the command:");
            str = reader.readLine();
            for (Map.Entry<String, Command> item : commandMap.entrySet()) {
                if (str.equals(item.getKey())) {
                    item.getValue().execute();
                }
            }
        }
    }
}