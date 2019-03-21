package ru.Hmelev.tm.command.project;

import ru.Hmelev.tm.bootstrap.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.command.util.Printer;
import ru.Hmelev.tm.entity.Project;
import ru.Hmelev.tm.entity.Task;

import java.io.IOException;

public class ProjectShowCommand extends Command {
    public ProjectShowCommand(Bootstrap bootstrap) {
        super(bootstrap, "project-show", "Show selected project.");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");

        System.out.println("ID project: ");
        idProject = reader.readLine();

        Project project = projectService.findProject(idProject);
        if (project != null) {
            Printer.showProject(project);
            for (Task task : taskService.listTaskIdProject(idProject)) {
                Printer.showTaskInProject(task);
            }
        }
        System.out.println("!!!DONE!!!");
    }
}