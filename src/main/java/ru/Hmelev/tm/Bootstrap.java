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

class Bootstrap {
    private Map<String, Command> commandMap = new HashMap<>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private ProjectsRepository projectsRepository = ProjectsRepository.getInstance();
    private TasksRepository tasksRepository = TasksRepository.getInstance();

    private ServiceProject serviceProject = new ServiceProject(projectsRepository);
    private ServiceTask serviceTask = new ServiceTask(tasksRepository);

    private Command command[] = new Command[]{
            new HelpCommand(),
            new ExitCommand(),
            new ProjectCreateCommand(reader, serviceProject, serviceTask),
            new ProjectClearCommand(reader, serviceProject, serviceTask),
            new ProjectListCommand(reader, serviceProject, serviceTask),
            new ProjectEditCommand(reader, serviceProject, serviceTask),
            new ProjectShowCommand(reader, serviceProject, serviceTask),
            new ProjectRemoveCommand(reader, serviceProject, serviceTask),
            new TaskCreateCommand(reader, serviceProject, serviceTask),
            new TaskClearCommand(reader, serviceProject, serviceTask),
            new TaskListCommand(reader, serviceProject, serviceTask),
            new TaskEditCommand(reader, serviceProject, serviceTask),
            new TaskShowCommand(reader, serviceProject, serviceTask),
            new TaskRemoveCommand(reader, serviceProject, serviceTask)
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