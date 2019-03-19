package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;
import java.util.UUID;

public class ProjectShowCommand extends Command {
    public ProjectShowCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-show", "Show selected project.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();

        do {
            System.out.println("ID project: ");
            id = reader.readLine();
        } while (!isUUIDValid(id));
        idProject = UUID.fromString(id);

        serviceProject.showProject(idProject);
        System.out.println("Tasks: ");
        for (Task task : serviceTask.listTaskIdProject(idProject)) {
            task.viewTask();
        }
        System.out.println("!!!DONE!!!");
    }
}