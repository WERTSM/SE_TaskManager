package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.entity.Project;

public class ProjectClearCommand extends Command {
    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-clear", "Remove all projects");
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