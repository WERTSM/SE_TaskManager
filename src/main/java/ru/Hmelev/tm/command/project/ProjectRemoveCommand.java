package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public class ProjectRemoveCommand extends Command {
    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-remove", "Remove selected project.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

        taskService.listTaskNoIdProject(idProject);
        projectService.removeProject(idProject);
        System.out.println("!!!DONE!!!");
    }
}