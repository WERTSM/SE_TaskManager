package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;

public class ProjectShowCommand extends Command {
    public ProjectShowCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-show", "Show selected project.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        projectService.listProject();

        do {
            System.out.println("ID project: ");
            idProject = reader.readLine();
        } while (!isUUIDValid(idProject));

        projectService.showProject(idProject);
        System.out.println("Tasks: ");
        for (Task task : taskService.listTaskIdProject(idProject)) {
            taskService.showTask(task.getId());
        }
        System.out.println("!!!DONE!!!");
    }
}