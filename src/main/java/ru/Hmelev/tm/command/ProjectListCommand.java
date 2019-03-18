package ru.Hmelev.tm.command;

import ru.Hmelev.tm.service.ServiceProject;
import ru.Hmelev.tm.service.ServiceTask;

import java.io.BufferedReader;

public class ProjectListCommand extends Command {
    public ProjectListCommand(BufferedReader reader, ServiceProject serviceProject, ServiceTask serviceTask) {

        super(reader, serviceProject, serviceTask, "project-list", "Show all projects.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();
        System.out.println("!!!DONE!!!");
    }
}