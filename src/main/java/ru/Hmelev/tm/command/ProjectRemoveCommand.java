package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

public class ProjectRemoveCommand extends Command {

    public ProjectRemoveCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {
        super(reader, serviceProject, serviceTask, "project-remove", "Remove selected project.");
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