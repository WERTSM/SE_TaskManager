package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;

public class ProjectRemoveCommand extends Command {
    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-remove", "Remove selected project.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        projectService.listProject();
        do {
            System.out.println("ID project: ");
            idProject = reader.readLine();
        } while (!isUUIDValid(idProject));

        taskService.listTaskNoIdProject(idProject);
        projectService.removeProject(idProject);
        System.out.println("!!!DONE!!!");
    }
}