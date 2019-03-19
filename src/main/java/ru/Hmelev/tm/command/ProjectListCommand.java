package ru.Hmelev.tm.command;

import ru.Hmelev.tm.Bootstrap;

public class ProjectListCommand extends Command {
    public ProjectListCommand(Bootstrap bootstrap) {

        super(bootstrap, "project-list", "Show all projects.");
    }

    @Override
    public void execute() {
        System.out.println("!!!Start command!!!");
        serviceProject.listProject();
        System.out.println("!!!DONE!!!");
    }
}