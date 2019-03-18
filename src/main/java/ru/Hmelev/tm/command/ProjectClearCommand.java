package ru.Hmelev.tm.command;

import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;

public class ProjectClearCommand extends Command {
    public ProjectClearCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "project-clear", "Remove all projects");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        for (Project project : serviceProject.findAllProjects()) {
            serviceTask.listTaskNoIdProject(project.getId());
        }
        serviceProject.clearProject();
        System.out.println("!!!DONE!!!");
    }
}