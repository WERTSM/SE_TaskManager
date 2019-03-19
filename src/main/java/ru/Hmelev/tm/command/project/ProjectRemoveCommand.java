package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

import java.io.IOException;
import java.util.UUID;

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
            id = reader.readLine();
        } while (!isUUIDValid(id));
        idProject = UUID.fromString(id);

        taskService.listTaskNoIdProject(idProject);
        projectService.removeProject(idProject);
        System.out.println("!!!DONE!!!");
    }
}