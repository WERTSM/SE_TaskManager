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

    ProjectsRepository projectsRepository = ProjectsRepository.getInstance();
    TasksRepository tasksRepository = TasksRepository.getInstance();

    ServiceProject serviceProject = new ServiceProject(projectsRepository);
    ServiceTask serviceTask = new ServiceTask(tasksRepository);

    String str;

    Command command[] = new Command[]{
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

    public void init() throws IOException, ParseException {
        for (Command commands : command) {
            commandMap.put(commands.getNameCommand(), commands);
        }
        while (!Thread.currentThread().isInterrupted()) {
            str = reader.readLine();
            for (Map.Entry<String, Command> item : commandMap.entrySet()) {
                if (str.equals(item.getKey())) {
                    item.getValue().execute();
                }
            }
        }
    }
}