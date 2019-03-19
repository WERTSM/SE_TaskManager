package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;

public class ProjectListCommand extends Command {
    public ProjectListCommand(Bootstrap bootstrap) {

        super(bootstrap, "project-list", "Show all projects.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        projectService.listProject();
        System.out.println("!!!DONE!!!");
    }
}