package ru.Hmelev.tm.command;

import ru.Hmelev.tm.entity.Task;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

public class ProjectShowCommand extends Command {
    public ProjectShowCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "project-show", "Show selected project.");
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