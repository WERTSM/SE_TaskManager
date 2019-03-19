package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

import java.io.IOException;
import java.util.UUID;

public class ProjectRemoveCommand extends Command {
    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-remove", "Remove selected project.");
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

        serviceTask.listTaskNoIdProject(idProject);
        serviceProject.removeProject(idProject);
        System.out.println("!!!DONE!!!");
    }
}