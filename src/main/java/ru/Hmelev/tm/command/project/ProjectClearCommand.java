package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Project;

public class ProjectClearCommand extends Command {
    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-clear", "Remove all projects");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        for (Project project : projectService.findAllProjects()) {
            taskService.listTaskNoIdProject(project.getId());
        }
        projectService.clearProject();
        System.out.println("!!!DONE!!!");
    }
}